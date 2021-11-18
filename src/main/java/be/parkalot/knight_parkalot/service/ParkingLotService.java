package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingLotCategory;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDto;
import be.parkalot.knight_parkalot.exceptions.ParkingLotCategoryNotFoundException;
import be.parkalot.knight_parkalot.mapper.ParkingLotMapper;
import be.parkalot.knight_parkalot.repository.ParkingLotCategoryRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.ParkingLotInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingLotService {

    private final Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

    private final ParkingLotRepository repository;
    private final ParkingLotMapper mapper;
    private final PostalCodeService postalCodeService;
    private final DivisionService divisionService;
    private final ParkingLotCategoryRepository categoryRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository repository, ParkingLotMapper mapper, PostalCodeService postalCodeService,
                             DivisionService divisionService, ParkingLotCategoryRepository categoryRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.postalCodeService = postalCodeService;
        this.divisionService = divisionService;
        this.categoryRepository = categoryRepository;
    }

    public ParkingLotDto createNewParkingLot(CreateParkingLotDto createParkingLotDto) {

        assertValidParkingLotDto(createParkingLotDto);

        PostalCode postalCodeContactPerson = postalCodeService.getPostalCode(createParkingLotDto.getContactPersonDto().getAddressDto().getPostalCodeDto());
        PostalCode postalCodeParkingLot = postalCodeService.getPostalCode(createParkingLotDto.getAddressDto().getPostalCodeDto());
        ParkingLotCategory parkingLotCategory = getParkingLotCategory(createParkingLotDto.getParkingLotCategoryId());
        Division division = divisionService.getDivisionById(createParkingLotDto.getDivisionId());

        ParkingLot newParkingLot = mapper.toEntity(createParkingLotDto, postalCodeContactPerson, postalCodeParkingLot, parkingLotCategory, division);
        return mapper.toDto(repository.save(newParkingLot));
    }

    private void assertValidParkingLotDto(CreateParkingLotDto parkingLotDto) {
        logger.info("assertValidParkingLotDto called");
        ParkingLotInputValidation parkingLotInputValidation = new ParkingLotInputValidation(parkingLotDto);
        parkingLotInputValidation.validate();
    }

    private ParkingLotCategory getParkingLotCategory(int categoryId) {
        assertParkingLotCategoryIdExistsInDatabase(categoryId);
        return categoryRepository.getById(categoryId);
    }

    private void assertParkingLotCategoryIdExistsInDatabase(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new ParkingLotCategoryNotFoundException("No parking lot category found with id: " + id);
        }
    }

}
