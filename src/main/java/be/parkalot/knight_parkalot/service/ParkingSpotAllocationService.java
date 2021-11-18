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

        // Check 0 -> Parking lot exists
        assertParkingLotExists(createParkingSpotAllocationDto.getParkingLotId());
        // Check 1 -> Check if not full
        assertParkingLotHasFreeSpots(createParkingSpotAllocationDto.getParkingLotId());
        // Check 2 -> MemberId exists
        assertMemberExists(createParkingSpotAllocationDto.getMemberId());
        // Check 3.a -> License plate = member's license plate
        // Check 3.b -> Membership = gold
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
}
