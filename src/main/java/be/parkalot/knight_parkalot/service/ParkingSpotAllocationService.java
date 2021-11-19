package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.*;
import be.parkalot.knight_parkalot.dto.CreateParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.exceptions.MemberNotFoundException;
import be.parkalot.knight_parkalot.exceptions.ParkingLotException;
import be.parkalot.knight_parkalot.mapper.ParkingSpotAllocationMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import be.parkalot.knight_parkalot.repository.ParkingSpotAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ParkingSpotAllocationService {

    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;
    private final ParkingSpotAllocationRepository parkingSpotAllocationRepository;
    private final MemberRepository memberRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingSpotAllocationService(ParkingSpotAllocationMapper parkingSpotAllocationMapper, ParkingSpotAllocationRepository parkingSpotAllocationRepository, MemberRepository memberRepository, ParkingLotRepository parkingLotRepository) {
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
        this.parkingSpotAllocationRepository = parkingSpotAllocationRepository;
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingSpotAllocationDto startAllocating(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {

        assertParkingLotExists(createParkingSpotAllocationDto.getParkingLotId());
        assertParkingLotHasFreeSpots(createParkingSpotAllocationDto.getParkingLotId());
        assertMemberExists(createParkingSpotAllocationDto.getMemberId());
        assertLicensePlateIsValid(createParkingSpotAllocationDto.getMemberId(), createParkingSpotAllocationDto.getLicensePlateNumber());

        Member member = memberRepository.getById(createParkingSpotAllocationDto.getMemberId());
        LicensePlate licensePlate = member.getLicensePlate();
        ParkingLot parkingLot = parkingLotRepository.getById(createParkingSpotAllocationDto.getParkingLotId());

        assertLicensePlateIsNotActiveYet(licensePlate);

        ParkingSpotAllocation allocation = parkingSpotAllocationMapper.toEntity(member, licensePlate, parkingLot);
        return parkingSpotAllocationMapper.toDto(parkingSpotAllocationRepository.save(allocation));
    }

    private void assertLicensePlateIsValid(int memberId, String licensePlateNumber) {
        Member member = memberRepository.getById(memberId);
        if (!member.getLicensePlate().getNumber().equals(licensePlateNumber)) {
            if (member.getMembershipLevel().getId() != MembershipLevel.GOLD_ID) {
                throw new ParkingLotException("License Plate does not belong to this member");
            }
        }
    }

    private void assertLicensePlateIsNotActiveYet(LicensePlate licensePlate) {
        ParkingSpotAllocation parkingSpotAllocation = parkingSpotAllocationRepository.findAllByLicensePlate(licensePlate).stream().filter(s -> !s.isInactive()).findFirst().orElse(null);
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
        return (int) parkingSpotAllocationRepository.findAll().stream().filter(s -> !s.isInactive())
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

    public List<ParkingSpotAllocationDto> getAll(Integer limit, String status, boolean descending) {
        int validatedLimit = limit == null ? 0 : limit;
        assertLimitGreaterThanOrEqualToZero(validatedLimit);

        String validatedStatus = status == null ? "all" : status;
        assertStatusIsValid(validatedStatus);

        List<ParkingSpotAllocation> result;

        if (descending) {
            result = parkingSpotAllocationRepository.findByOrderByStartingTimeDesc();
        } else {
            result = parkingSpotAllocationRepository.findByOrderByStartingTimeAsc();
        }

        if (validatedStatus.equalsIgnoreCase("active")) {
            result = result.stream().filter(p -> !p.isInactive()).toList();
        } else if (validatedStatus.equalsIgnoreCase("passive")) {
            result = result.stream().filter(ParkingSpotAllocation::isInactive).toList();
        }

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

        if (parkingSpotAllocation.getMember().getId() != memberId) {
            throw new ParkingLotException("The provided member has to be the owner of the parking spot!");
        }

        if (parkingSpotAllocation.isInactive()) {
            throw new ParkingLotException("The provided parking spot is already inactive!");
        }

        parkingSpotAllocation.setInactive(true);
        parkingSpotAllocation.setEndingTime(LocalDateTime.now());

        return parkingSpotAllocationMapper.toDto(parkingSpotAllocation);
    }
}
