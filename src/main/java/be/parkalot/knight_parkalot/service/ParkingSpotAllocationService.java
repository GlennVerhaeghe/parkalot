package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.dto.CreateParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.dto.ParkingSpotAllocationDto;
import be.parkalot.knight_parkalot.mapper.ParkingSpotAllocationMapper;
import be.parkalot.knight_parkalot.repository.MemberRepository;
import be.parkalot.knight_parkalot.repository.ParkingSpotAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotAllocationService {

    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;
    private final ParkingSpotAllocationRepository parkingSpotAllocationRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ParkingSpotAllocationService(ParkingSpotAllocationMapper parkingSpotAllocationMapper, ParkingSpotAllocationRepository parkingSpotAllocationRepository, MemberRepository memberRepository) {
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
        this.parkingSpotAllocationRepository = parkingSpotAllocationRepository;
        this.memberRepository = memberRepository;
    }

    public ParkingSpotAllocationDto startAllocating(CreateParkingSpotAllocationDto createParkingSpotAllocationDto){
        //checks
        return null;
    }
}
