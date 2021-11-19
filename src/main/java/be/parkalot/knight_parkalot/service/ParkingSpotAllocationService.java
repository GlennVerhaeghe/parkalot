package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.*;
import be.parkalot.knight_parkalot.dto.CreateParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.dto.LicensePlateDto;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.exceptions.MemberNotFoundException;
import be.parkalot.knight_parkalot.exceptions.ParkingLotException;
import be.parkalot.knight_parkalot.mapper.LicensePlateMapper;
import be.parkalot.knight_parkalot.mapper.ParkingSpotAllocationMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import be.parkalot.knight_parkalot.repository.ParkingSpotAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingSpotAllocationService {

    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;
    private final ParkingSpotAllocationRepository parkingSpotAllocationRepository;
    private final MemberRepository memberRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final LicensePlateService licensePlateService;
    private final LicensePlateMapper licensePlateMapper;

    @Autowired
    public ParkingSpotAllocationService(ParkingSpotAllocationMapper parkingSpotAllocationMapper, ParkingSpotAllocationRepository parkingSpotAllocationRepository, MemberRepository memberRepository, ParkingLotRepository parkingLotRepository, LicensePlateService licensePlateService, LicensePlateMapper licensePlateMapper) {
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
        this.parkingSpotAllocationRepository = parkingSpotAllocationRepository;
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.licensePlateService = licensePlateService;
        this.licensePlateMapper = licensePlateMapper;
    }

    public ParkingSpotAllocationDto startAllocating(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {

        assertParkingLotExists(createParkingSpotAllocationDto.getParkingLotId());
        assertParkingLotHasFreeSpots(createParkingSpotAllocationDto.getParkingLotId());
        assertMemberExists(createParkingSpotAllocationDto.getMemberId());
        assertLicensePlateIsValid(createParkingSpotAllocationDto.getMemberId(), createParkingSpotAllocationDto.getLicensePlateDto());

        Member member = memberRepository.getById(createParkingSpotAllocationDto.getMemberId());

        LicensePlate licensePlate = getLicensePlate(createParkingSpotAllocationDto);
        ParkingLot parkingLot = parkingLotRepository.getById(createParkingSpotAllocationDto.getParkingLotId());

        assertLicensePlateIsNotActiveYet(licensePlate);

        ParkingSpotAllocation allocation = parkingSpotAllocationMapper.toEntity(member, licensePlate, parkingLot);
        return parkingSpotAllocationMapper.toDto(parkingSpotAllocationRepository.save(allocation));
    }

    private LicensePlate getLicensePlate(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        return licensePlateService.getLicensePlateNumber(createParkingSpotAllocationDto.getLicensePlateDto());
    }

    private void assertLicensePlateIsValid(int memberId, LicensePlateDto licensePlate) {
        Member member = memberRepository.getById(memberId);

        if (!member.getLicensePlate().getNumber().equals(licensePlate.getNumber())) {
            if (member.getMembershipLevel().getId() != MembershipLevel.GOLD_ID) {
                throw new ParkingLotException("License Plate does not belong to this member");
            }
        }
    }

    private void assertLicensePlateIsNotActiveYet(LicensePlate licensePlate) {
        System.out.println(licensePlate.getNumber() + " " + licensePlate.getCountryCode());
        ParkingSpotAllocation parkingSpotAllocation = parkingSpotAllocationRepository.findAllByLicensePlate(licensePlate).stream()
                .filter(s -> s.getStatus().isActive()).findFirst().orElse(null);
        if (parkingSpotAllocation != null) {
            throw new ParkingLotException("License Plate already has an allocated parking spot");
        }
    }

    private void assertMemberExists(int memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberNotFoundException("This member does not exist in our database");
        }
    }

    private void assertParkingLotHasFreeSpots(int parkingLotId) {
        if (parkingLotRepository.getById(parkingLotId).getMaxCapacity() <= usedParkSpots(parkingLotId)) {
            throw new ParkingLotException("No free spaces left");
        }
    }


    private int usedParkSpots(int parkingLotId) {
        return (int) parkingSpotAllocationRepository.findAll().stream()
                .filter(s -> s.getStatus().isActive())
                .filter(id -> id.getParkingLot().getId() == parkingLotId)
                .count();
    }

    private void assertParkingLotExists(int parkingLotId) {
        if (!parkingLotRepository.existsById(parkingLotId)) {
            throw new ParkingLotException("No parking lot with this id is found " + parkingLotId);
        }
    }

    private void assertParkingSpotAllocationExists(int parkingSpotAllocationId) {
        if (!parkingSpotAllocationRepository.existsById(parkingSpotAllocationId)) {
            throw new ParkingLotException("No parking spot with this id is found " + parkingSpotAllocationId);
        }
    }

    public List<ParkingSpotAllocationDto> getAllParkingAllocations(Integer limit, String allocationStatus, boolean descending) {
        int validatedLimit = limit == null ? 0 : limit;
        assertLimitGreaterThanOrEqualToZero(validatedLimit);

        String validatedStatus = getValidatedAllocationStatus(allocationStatus);

        List<ParkingSpotAllocation> result;

        if (descending) {
            result = parkingSpotAllocationRepository.findByOrderByStartingTimeDesc();
        } else {
            result = parkingSpotAllocationRepository.findByOrderByStartingTimeAsc();
        }

        result = getFilteredParkingLotListByAllocationStatus(result, validatedStatus);

        if (validatedLimit != 0) {
            result = result.stream().limit(validatedLimit).toList();
        }
        return result.stream().map(parkingSpotAllocationMapper::toDto).toList();
    }

    private void assertStatusIsValid(String status) {
        if (!status.equalsIgnoreCase("all") && !status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("passive")) {
            throw new IllegalArgumentException("No valid status");
        }
    }

    public void assertLimitGreaterThanOrEqualToZero(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
    }

    public ParkingSpotAllocationDto stopAllocating(Integer memberId, Integer parkingSpotAllocationId) {
        assertParkingSpotAllocationExists(parkingSpotAllocationId);
        ParkingSpotAllocation parkingSpotAllocation = parkingSpotAllocationRepository.getById(parkingSpotAllocationId);

        assertCanStopAllocatingParkingSpot(parkingSpotAllocation, memberId);

        parkingSpotAllocation.setStatus(ParkingSpotAllocationStatus.INACTIVE);
        parkingSpotAllocation.setEndingTime(LocalDateTime.now());

        return parkingSpotAllocationMapper.toDto(parkingSpotAllocation);
    }

    public void assertCanStopAllocatingParkingSpot(ParkingSpotAllocation parkingSpotAllocation, int memberId) {
        if (parkingSpotAllocation.getMember().getId() != memberId) {
            throw new ParkingLotException("The provided member has to be the owner of the parking spot!");
        }

        if (!parkingSpotAllocation.getStatus().isActive()) {
            throw new ParkingLotException("The provided parking spot is already inactive!");
        }
    }


    public List<ParkingSpotAllocationDto> getAllParkingAllocationsByMember(int memberId, String allocationStatus) {
        assertMemberExists(memberId);
        String validatedStatus = getValidatedAllocationStatus(allocationStatus);

        Member member = memberRepository.getById(memberId);
        List<ParkingSpotAllocation> parkingSpotAllocations = getFilteredParkingLotListByAllocationStatus(parkingSpotAllocationRepository.findAllByMember(member), validatedStatus);

        return parkingSpotAllocations.stream().map(parkingSpotAllocationMapper::toDto).collect(Collectors.toList());
    }

    public List<ParkingSpotAllocationDto> getAllParkingAllocationsByParkingLot(int parkingLotId, String allocationStatus) {
        assertParkingLotExists(parkingLotId);
        ParkingLot parkingLot = parkingLotRepository.getById(parkingLotId);

        String validatedStatus = getValidatedAllocationStatus(allocationStatus);
        List<ParkingSpotAllocation> parkingSpotAllocations = getFilteredParkingLotListByAllocationStatus(parkingSpotAllocationRepository.findAllByParkingLot(parkingLot), validatedStatus);

        return parkingSpotAllocations.stream().map(parkingSpotAllocationMapper::toDto).collect(Collectors.toList());
    }

    public List<ParkingSpotAllocation> getFilteredParkingLotListByAllocationStatus(List<ParkingSpotAllocation> baseParkingSpotAllocationList, String allocationStatus) {
        if (allocationStatus.equalsIgnoreCase("active")) {
            return baseParkingSpotAllocationList.stream().filter(p -> p.getStatus().isActive()).toList();
        } else if (allocationStatus.equalsIgnoreCase("passive")) {
            return baseParkingSpotAllocationList.stream().filter(p -> !p.getStatus().isActive()).toList();
        }
        return baseParkingSpotAllocationList;
    }

    public String getValidatedAllocationStatus(String allocationStatus) {
        String validatedStatus = allocationStatus == null ? "all" : allocationStatus;
        assertStatusIsValid(validatedStatus);
        return validatedStatus;
    }

    public List<ParkingSpotAllocation> getAllInactiveParkingAllocationsByMember(Member member) {
        return parkingSpotAllocationRepository.findAllByMemberAndStatus(member, ParkingSpotAllocationStatus.INACTIVE);
    }
}
