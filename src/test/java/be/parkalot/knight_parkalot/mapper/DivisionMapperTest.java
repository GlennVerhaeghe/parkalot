package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.dto.CreateDivisionDto;
import be.parkalot.knight_parkalot.dto.CreateNameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DivisionMapperTest {

    private DivisionMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DivisionMapper(new NameMapper());
    }
//
//    @Test
//    void toEntity_whenParentIdIsZero_returnsDivision() {
//        //when
//        Division result = mapper.toEntity(new CreateDivisionDto("TestName", "TestOldName",
//                new CreateNameDto("TestFirstName", "TestLastName"), 0));
//        //then
//        assertNotNull(result);
//        assertNull(result.getParentDivision());
//    }
//
//    @Test
//    void toEntity_whenParentIdIsNotNullOrZero_returnsSubDivision() {
//        //when
//        Division result = mapper.toEntity(new CreateDivisionDto("TestName", "TestOldName",
//                new CreateNameDto("TestFirstName", "TestLastName"), 1));
//        //then
//        assertNotNull(result);
//        assertNotNull(result.getParentDivision());
//    }
}