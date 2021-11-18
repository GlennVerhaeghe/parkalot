package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingLotCategory;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.*;
import be.parkalot.knight_parkalot.exceptions.MissingArgumentsException;
import be.parkalot.knight_parkalot.mapper.ParkingLotMapper;
import be.parkalot.knight_parkalot.repository.ParkingLotCategoryRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ParkingLotServiceTest {

    private ParkingLotService parkingLotService;
    private ParkingLotRepository mockParkingLotRepository;
    private ParkingLotMapper mockParkingLotMapper;
    private PostalCodeService mockPostalCodeService;
    private DivisionService mockDivisionService;
    private ParkingLotCategoryRepository mockParkingLotCategoryRepository;

    private CreateContactPersonDto contactPersonDto;

    @BeforeEach
    void setUp() {
        mockParkingLotRepository = Mockito.mock(ParkingLotRepository.class);
        mockParkingLotMapper = Mockito.mock(ParkingLotMapper.class);
        mockPostalCodeService = Mockito.mock(PostalCodeService.class);
        mockDivisionService = Mockito.mock(DivisionService.class);
        mockParkingLotCategoryRepository = Mockito.mock(ParkingLotCategoryRepository.class);
        parkingLotService = new ParkingLotService(mockParkingLotRepository, mockParkingLotMapper, mockPostalCodeService,
                mockDivisionService, mockParkingLotCategoryRepository);

        //GIVEN
        contactPersonDto = new CreateContactPersonDto(
                new NameDto("Thomas", "opheide"),
                "0476502479",
                "013323458",
                "thomasopheide@gmail.com",
                new AddressDto("Teststraat", "1", new PostalCodeDto(3560, "Lummen"))
        );
    }

    @Test
    void createParkingLot_whenValid_thenCallMethodsSaveToRepo() {
        //GIVEN
        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto(
                "Test Parkinglot",
                100,
                contactPersonDto,
                new AddressDto("Parkinglotstraat", "5", new PostalCodeDto(3560, "Lummen")),
                2.5D,
                1,
                1
        );
        Mockito.when(mockParkingLotRepository.save(Mockito.any(ParkingLot.class))).thenReturn(new ParkingLot());
        Mockito.when(mockPostalCodeService.getPostalCode(Mockito.any(PostalCodeDto.class))).thenReturn(new PostalCode());
        Mockito.when(mockParkingLotMapper.toEntity(Mockito.any(CreateParkingLotDto.class), Mockito.any(PostalCode.class),
                Mockito.any(PostalCode.class), Mockito.any(ParkingLotCategory.class), Mockito.any(Division.class))).thenReturn(new ParkingLot());
        Mockito.when(mockParkingLotMapper.toDto(Mockito.any(ParkingLot.class))).thenReturn(new ParkingLotDto());
        Mockito.when(mockDivisionService.getDivisionById(Mockito.anyInt())).thenReturn(new Division());
        Mockito.when(mockParkingLotCategoryRepository.getById(Mockito.anyInt())).thenReturn(new ParkingLotCategory());
        Mockito.when(mockParkingLotCategoryRepository.existsById(Mockito.anyInt())).thenReturn(true);

        //WHEN
        parkingLotService.createNewParkingLot(createParkingLotDto);

        //THEN
        Mockito.verify(mockParkingLotRepository, Mockito.times(1)).save(Mockito.any(ParkingLot.class));
    }

    @Test
    void createParkingLot_whenParkingLotNameIsNullOrBlank_thenThrowException() {
        //GIVEN
        CreateParkingLotDto createParkingLotDtoWithNameNull = new CreateParkingLotDto(
                null,
                100,
                contactPersonDto,
                new AddressDto("Parkinglotstraat", "5", new PostalCodeDto(3560, "Lummen")),
                2.5D,
                1,
                1
        );
        CreateParkingLotDto createParkingLotDtoWithNameBlank = new CreateParkingLotDto(
                "",
                100,
                contactPersonDto,
                new AddressDto("Parkinglotstraat", "5", new PostalCodeDto(3560, "Lummen")),
                2.5D,
                1,
                1
        );

        //THEN
        assertThrows(MissingArgumentsException.class, () -> parkingLotService.createNewParkingLot(createParkingLotDtoWithNameNull));
        assertThrows(MissingArgumentsException.class, () -> parkingLotService.createNewParkingLot(createParkingLotDtoWithNameBlank));
    }

    @Test
    void createParkingLot_whenMaxCapacityOrPriceIsNegative_thenThrowException() {
        //GIVEN
        CreateParkingLotDto createParkingLotDtoWithCapacityNegative = new CreateParkingLotDto(
                "Parking 1",
                -10,
                contactPersonDto,
                new AddressDto("Parkinglotstraat", "5", new PostalCodeDto(3560, "Lummen")),
                2.5D,
                1,
                1
        );
        CreateParkingLotDto createParkingLotDtoWithPriceNegative = new CreateParkingLotDto(
                "Parking 2",
                100,
                contactPersonDto,
                new AddressDto("Parkinglotstraat", "5", new PostalCodeDto(3560, "Lummen")),
                -5D,
                1,
                1
        );

        //THEN
        assertThrows(IllegalArgumentException.class, () -> parkingLotService.createNewParkingLot(createParkingLotDtoWithCapacityNegative));
        assertThrows(IllegalArgumentException.class, () -> parkingLotService.createNewParkingLot(createParkingLotDtoWithPriceNegative));
    }
}