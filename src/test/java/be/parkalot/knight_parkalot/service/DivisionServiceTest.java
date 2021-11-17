package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.CreateNameDto;
import be.parkalot.knight_parkalot.mapper.DivisionMapper;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DivisionServiceTest {

    private DivisionService divisionService;
    private DivisionRepository mockRepository;
    private DivisionMapper mockDivisionMapper;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(DivisionRepository.class);
        mockDivisionMapper = Mockito.mock(DivisionMapper.class);
        divisionService = new DivisionService(mockRepository, mockDivisionMapper);
    }
//
//    @Test
//    void createNewDivision_whenDivisionDtoValid_thenCallSaveToRepo() {
//        //given
//        Division mappedDivision = Division.createDivision("TestName", "TestOldName",
//                new Name("TestFirstName", "TestLastName"));
//        CreateDivisionDto createDivisionDto = new CreateDivisionDto("TestName", "TestOldName",
//                new CreateNameDto("TestFirstName", "TestLastName"), 0);
//        Mockito.when(mockDivisionMapper.toEntity(createDivisionDto)).thenReturn(mappedDivision);
//
//        //when
//        divisionService.createNewDivision(createDivisionDto);
//        //the
//        Mockito.verify(mockDivisionMapper, Mockito.times(1)).toEntity(createDivisionDto);
//        Mockito.verify(mockRepository, Mockito.times(1)).save(mappedDivision);
//    }
}