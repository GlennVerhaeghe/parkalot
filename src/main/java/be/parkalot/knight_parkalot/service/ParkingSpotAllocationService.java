package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.domain.Member;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingSpotAllocation;
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

import java.util.List;

@Service
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

        ParkingSpotAllocation allocation = parkingSpotAllocationMapper.toEntity(member, licensePlate, parkingLot);
        return parkingSpotAllocationMapper.toDto(parkingSpotAllocationRepository.save(allocation));
    }

    private void assertLicensePlateIsValid(int memberId, String licensePlateNumber) {
        Member member = memberRepository.getById(memberId);
        if (!member.getLicensePlate().getNumber().equals(licensePlateNumber)) {
            if (member.getMembershipLevel().getId() != 3) {
                throw new ParkingLotException("License Plate does not belong to this member");
            }
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
        return (int) parkingSpotAllocationRepository.findAll().stream().filter(s -> !s.isStopNow())
                .filter(id -> id.getParkingLot().getId() == parkingLotId)
                .count();
    }

    private void assertParkingLotExists(int parkingLotId) {
        if (!parkingLotRepository.existsById(parkingLotId)) {
            throw new ParkingLotException("No parking lot with this id is found");
        }
    }

    public List<ParkingSpotAllocationDto> getAll(int limit, String status, boolean descending) {
        assertLimitGreaterThanOrEqualToZero(limit);
        assertStatusIsValid(status);

        List<ParkingSpotAllocation> result;

        if(descending) {
            result = parkingSpotAllocationRepository.findAllOrderByStartingTimeDesc();
        } else {
            result = parkingSpotAllocationRepository.findAllOrderByStartingTimeAsc();
        }

        if(status.equalsIgnoreCase("active")) {
            result = result.stream().filter(p -> !p.isStopNow()).toList();
        } else if (status.equalsIgnoreCase("passive")) {
            result = result.stream().filter(ParkingSpotAllocation::isStopNow).toList();
        }

        if(limit != 0) {
            result = result.stream().limit(limit).toList();
        }
        return result.stream().map(parkingSpotAllocationMapper::toDto).toList();
    }

    private void assertStatusIsValid(String status) {
        if(status != null && !status.equalsIgnoreCase("all") && !status.equalsIgnoreCase("active") && !status.equalsIgnoreCase("passive")) {
            throw new IllegalArgumentException("No valid status");
        }
    }

    public void assertLimitGreaterThanOrEqualToZero(int limit) {
        if(limit<0) {
            throw new IllegalArgumentException("Limit must be greater than 0");
        }
    }
}
