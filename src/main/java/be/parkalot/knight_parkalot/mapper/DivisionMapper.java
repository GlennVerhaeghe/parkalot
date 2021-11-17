package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DivisionMapper {
    private final NameMapper nameMapper;

    @Autowired
    public DivisionMapper(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    public Division toEntity(CreateDivisionDto divisionDto, Division parent) {
        Name directorName = nameMapper.toEntity(divisionDto.getDirectorName());
        return new Division(divisionDto.getName(), divisionDto.getOldName(), directorName, parent);
    }

    public DivisionDto toDto(Division division) {
        return new DivisionDto.Builder()
                .withId(division.getId())
                .withName(division.getDivisionName())
                .withOldName(division.getOriginalName())
                .withDirectorName(nameMapper.toDto(division.getDirectorName()))
                .withParentId(division.getParentId())
                .build();
    }

}
