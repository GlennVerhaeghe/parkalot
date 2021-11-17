package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    private final DivisionRepository divisionRepository;
    private final NameMapper nameMapper;

    @Autowired
    public DivisionMapper(DivisionRepository divisionRepository, NameMapper nameMapper) {
        this.divisionRepository = divisionRepository;
        this.nameMapper = nameMapper;
    }

    public Division toEntity(CreateDivisionDto divisionDto) {
        Division parent = divisionRepository.getById(divisionDto.getParentId());
        Name directorName = nameMapper.toEntity(divisionDto.getDirectorName());
        return new Division(divisionDto.getName(), divisionDto.getOldName(), directorName, parent);
    }
}
