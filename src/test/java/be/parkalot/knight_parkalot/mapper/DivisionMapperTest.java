package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.CreateNameDto;
import be.parkalot.knight_parkalot.repository.DivisionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DivisionMapperTest {

    private DivisionRepository mockRepository;
    private DivisionMapper mapper;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(DivisionRepository.class);
        mapper = new DivisionMapper(mockRepository, new NameMapper());
    }

    @Test
    void toEntity_whenParentIdIsNull_returnsDivision() {
        //when
        Division result = mapper.toEntity(new CreateDivisionDto("TestName", "TestOldName",
                new CreateNameDto("TestFirstName", "TestLastName"), null));
        //then
        Mockito.verify(mockRepository, Mockito.never()).getById(Mockito.anyInt());
        assertNotNull(result);
        assertNull(result.getParentDivision());
    }

    @Test
    void toEntity_whenParentIdIsZero_returnsDivision() {
        //when
        Division result = mapper.toEntity(new CreateDivisionDto("TestName", "TestOldName",
                new CreateNameDto("TestFirstName", "TestLastName"), 0));
        //then
        Mockito.verify(mockRepository, Mockito.never()).getById(Mockito.anyInt());
        assertNotNull(result);
        assertNull(result.getParentDivision());
    }

    @Test
    void toEntity_whenParentIdIsNotNullOrZero_returnsSubDivision() {
        //given
        Mockito.when(mockRepository.getById(Mockito.anyInt())).thenReturn(
                Division.createSubDivision("TestName", "TestOldName",
                        new Name("TestFirstName", "TestLastName"), new Division()));
        //when
        Division result = mapper.toEntity(new CreateDivisionDto("TestName", "TestOldName",
                new CreateNameDto("TestFirstName", "TestLastName"), 1));
        //then
        Mockito.verify(mockRepository, Mockito.times(1)).getById(1);
        assertNotNull(result);
        assertNotNull(result.getParentDivision());
    }
}