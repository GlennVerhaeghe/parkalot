package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.division.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.NameDto;
import be.parkalot.knight_parkalot.exceptions.DivisionNotFoundException;
import be.parkalot.knight_parkalot.mapper.DivisionMapper;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DivisionServiceTest {

    private DivisionService service;
    private DivisionRepository mockRepository;
    private DivisionMapper mockMapper;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(DivisionRepository.class);
        mockMapper = Mockito.mock(DivisionMapper.class);
        service = new DivisionService(mockRepository, mockMapper);
    }

    @Test
    void createNewDivision_whenDivisionDtoValid_thenCallMethodsSaveToRepo() {
        //given
        CreateDivisionDto createDivisionDto = new CreateDivisionDto("TestName", "TestOldName",
                new NameDto("TestFirstName", "TestLastName"), 0);
        Division mappedDivision = new Division("TestName", "TestOldName",
                new Name("TestFirstName", "TestLastName"), null);
        Mockito.when(mockMapper.toEntity(createDivisionDto, null)).thenReturn(mappedDivision);
        Mockito.when(mockRepository.save(Mockito.any(Division.class))).thenReturn(new Division());
        //when
        service.createNewDivision(createDivisionDto);
        //then
        Mockito.verify(mockMapper, Mockito.times(1)).toEntity(createDivisionDto, null);
        Mockito.verify(mockRepository, Mockito.times(1)).save(mappedDivision);
        Mockito.verify(mockMapper, Mockito.times(1)).toDto(Mockito.any(Division.class));
    }

    @Test
    void createNewDivision_whenParentIdNotExists_thenThrowException() {
        //given
        CreateDivisionDto dto = new CreateDivisionDto("TestName", "TestOGName",
                new NameDto("firstName", "lastName"), 5);
        //when
        Mockito.when(mockRepository.existsById(5)).thenReturn(false);
        //then
        assertThrows(DivisionNotFoundException.class, () -> service.createNewDivision(dto));
    }

    @Test
    void createNewDivision_whenParentIdIsZero_thenParentIsNull() {
        //given
        CreateDivisionDto dto = new CreateDivisionDto("TestName", "TestOGName",
                new NameDto("firstName", "lastName"), 0);
        //when
        service.createNewDivision(dto);
        //then
        Mockito.verify(mockMapper).toEntity(dto, null);
    }
}