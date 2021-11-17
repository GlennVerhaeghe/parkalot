package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.mapper.DivisionMapper;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    private final DivisionRepository divisionRepository;
    private final DivisionMapper mapper;

    @Autowired
    public DivisionService(DivisionRepository divisionRepository, DivisionMapper mapper) {
        this.divisionRepository = divisionRepository;
        this.mapper = mapper;
    }

    public Division createNewDivision(CreateDivisionDto divisionDto) {
        Division division = mapper.toEntity(divisionDto);
        return divisionRepository.save(division);
    }
}
