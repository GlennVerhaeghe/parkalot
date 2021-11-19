package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.PostalCode;
import be.parkalot.knight_parkalot.dto.PostalCodeDto;
import be.parkalot.knight_parkalot.mapper.PostalCodeMapper;
import be.parkalot.knight_parkalot.repository.PostalCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostalCodeService {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final PostalCodeRepository repository;
    private final PostalCodeMapper postalCodeMapper;

    @Autowired
    public PostalCodeService(PostalCodeRepository repository, PostalCodeMapper postalCodeMapper) {
        this.repository = repository;
        this.postalCodeMapper = postalCodeMapper;
    }

    public PostalCode getPostalCode(PostalCodeDto postalCodeDto) {
        logger.info("getPostalCode called");
        Optional<PostalCode> postalCodeOptional = repository.findById(postalCodeDto.getCode());
        return postalCodeOptional.orElseGet(() -> repository.save(postalCodeMapper.toEntity(postalCodeDto)));
    }

}
