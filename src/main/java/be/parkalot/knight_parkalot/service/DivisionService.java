package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import be.parkalot.knight_parkalot.exceptions.DivisionNotFoundException;
import be.parkalot.knight_parkalot.mapper.DivisionMapper;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.DivisionInputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    private final DivisionRepository repository;
    private final DivisionMapper mapper;

    @Autowired
    public DivisionService(DivisionRepository repository, DivisionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DivisionDto createNewDivision(CreateDivisionDto divisionDto) {
        Division parent = null;
        DivisionInputValidation inputValidation = new DivisionInputValidation(divisionDto);
        inputValidation.validate();
        if (isForSubDivision(divisionDto)) {
            parent = repository.getById(divisionDto.getParentId());
        }
        Division division = mapper.toEntity(divisionDto, parent);
        return mapper.toDto(repository.save(division));
    }

    private boolean isForSubDivision(CreateDivisionDto divisionDto) {
        if (divisionDto.getParentId() == 0) {
            return false;
        }
        assertIdExistsInDatabase(divisionDto.getParentId());
        return true;
    }

    private void assertIdExistsInDatabase(int divisionId) {
        if (!repository.existsById(divisionId)) {
            throw new DivisionNotFoundException("No division found with id: " + divisionId);
        }
    }

    public List<DivisionDto> getAllDivisions() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public DivisionDto getDivisionDtoById(int divisionId) {
        return mapper.toDto(getDivisionById(divisionId));
    }

    public Division getDivisionById(int divisionId) {
        assertIdExistsInDatabase(divisionId);
        return repository.findById(divisionId).get();
    }

}
