package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.ContactPerson;
import be.parkalot.knight_parkalot.domain.Division;
import be.parkalot.knight_parkalot.domain.ParkingLot;
import be.parkalot.knight_parkalot.domain.ParkingLotCategory;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.ContactPersonDto;
import be.parkalot.knight_parkalot.dto.CreateParkingLotDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDetailsDto;
import be.parkalot.knight_parkalot.dto.ParkingLotDto;
import be.parkalot.knight_parkalot.exceptions.ParkingLotCategoryNotFoundException;
import be.parkalot.knight_parkalot.exceptions.ParkingLotNotFoundException;
import be.parkalot.knight_parkalot.mapper.ParkingLotMapper;
import be.parkalot.knight_parkalot.repository.ContactPersonRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotCategoryRepository;
import be.parkalot.knight_parkalot.repository.ParkingLotRepository;
import be.parkalot.knight_parkalot.service.inputvalidation.ParkingLotInputValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingLotService {

    private final Logger logger = LoggerFactory.getLogger(ParkingLotService.class);

    private final ParkingLotRepository repository;
    private final ParkingLotMapper mapper;
    private final PostalCodeService postalCodeService;
    private final DivisionService divisionService;
    private final ParkingLotCategoryRepository categoryRepository;
    private final ContactPersonService contactPersonService;

    @Autowired
    public ParkingLotService(ParkingLotRepository repository, ParkingLotMapper mapper, PostalCodeService postalCodeService,
                             DivisionService divisionService, ParkingLotCategoryRepository categoryRepository, ContactPersonService contactPersonService) {
        this.repository = repository;
        this.mapper = mapper;
        this.postalCodeService = postalCodeService;
        this.divisionService = divisionService;
        this.categoryRepository = categoryRepository;
        this.contactPersonService = contactPersonService;
    }

    public ParkingLotDetailsDto createNewParkingLot(CreateParkingLotDto createParkingLotDto) {

        assertValidParkingLotDto(createParkingLotDto);

        PostalCode postalCodeParkingLot = postalCodeService.getPostalCode(createParkingLotDto.getAddressDto().getPostalCodeDto());
        ParkingLotCategory parkingLotCategory = getParkingLotCategory(createParkingLotDto.getParkingLotCategoryId());
        Division division = divisionService.getDivisionById(createParkingLotDto.getDivisionId());
        ContactPerson contactPerson = contactPersonService.extractContactPersonForParkingLot(createParkingLotDto);


        ParkingLot newParkingLot = mapper.toEntity(createParkingLotDto, contactPerson, postalCodeParkingLot, parkingLotCategory, division);
        return mapper.toDetailsDto(repository.save(newParkingLot));
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

    public List<ParkingLotDto> getAllParkingLots() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ParkingLotDetailsDto getParkingLotById(int parkingLotID) {
        assertIdExistsInDatabase(parkingLotID);
        return mapper.toDetailsDto(repository.getById(parkingLotID));
    }

    private void assertIdExistsInDatabase(int parkingLotID) {
        logger.info("assertIdExistsInDatabase called");
        if (!repository.existsById(parkingLotID)) {
            throw new ParkingLotNotFoundException("No Parking found with id: " + parkingLotID);
        }
    }
}
