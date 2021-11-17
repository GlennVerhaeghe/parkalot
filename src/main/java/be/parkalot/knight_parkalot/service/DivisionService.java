package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import be.parkalot.knight_parkalot.mapper.DivisionMapper;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (repository.existsById(divisionDto.getParentId())) {
            parent = repository.getById(divisionDto.getParentId());
        }
        Division division = mapper.toEntity(divisionDto, parent);
        return mapper.toDto(repository.save(division));
    }
}
