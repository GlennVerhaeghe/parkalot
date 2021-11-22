package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.ContactPerson;
import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.parkingLot.CreateParkingLotDto;
import be.parkalot.knight_parkalot.mapper.ContactPersonMapper;
import be.parkalot.knight_parkalot.repository.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactPersonService {

    private final ContactPersonRepository repository;
    private final ContactPersonMapper mapper;
    private final PostalCodeService postalCodeService;

    @Autowired
    public ContactPersonService(ContactPersonRepository repository, ContactPersonMapper mapper, PostalCodeService postalCodeService) {
        this.repository = repository;
        this.mapper = mapper;
        this.postalCodeService = postalCodeService;
    }

    public ContactPerson extractContactPersonForParkingLot(CreateParkingLotDto createParkingLotDto) {
        PostalCode postalCode = postalCodeService.getPostalCode(createParkingLotDto.getContactPersonDto().getAddressDto().getPostalCodeDto());
        if (repository.existsByEmail(createParkingLotDto.getContactPersonDto().getEmail())) {
            return repository.getByEmail(createParkingLotDto.getContactPersonDto().getEmail());
        }
        ContactPerson contactPerson = mapper.toEntity(createParkingLotDto.getContactPersonDto(), postalCode);
        return repository.save(contactPerson);
    }
}
