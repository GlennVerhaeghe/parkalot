package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.DivisionDto;
import be.parkalot.knight_parkalot.dto.NameDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionMapperTest {

    private final NameMapper nameMapper = new NameMapper();
    private final DivisionMapper divisionMapper = new DivisionMapper(nameMapper);

    @Test
    void toEntity() {
        //given
        CreateDivisionDto dto = new CreateDivisionDto("Name", "Old name", new NameDto("Tom", "Hanks"), 0);
        Division expected = new Division("Name", "Old name", new Name("Tom", "Hanks"), null);
        //when
        Division result = divisionMapper.toEntity(dto, null);
        //then
        assertEquals(expected, result);
    }

    @Test
    void toDto() {
        //given
        Division division = new Division("Name", "Old name", new Name("Tom", "Hanks"), null);
        DivisionDto expected = new DivisionDto.Builder()
                .withId(division.getId())
                .withName("Name")
                .withOldName("Old name")
                .withDirectorName(new NameDto("Tom", "Hanks"))
                .withParentId(0)
                .build();
        //when
        DivisionDto result = divisionMapper.toDto(division);
        //then
        assertEquals(expected, result);
    }
}