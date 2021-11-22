package be.parkalot.knight_parkalot.service;

import be.parkalot.knight_parkalot.domain.LicensePlate;
import be.parkalot.knight_parkalot.dto.member.LicensePlateDto;
import be.parkalot.knight_parkalot.mapper.LicensePlateMapper;
import be.parkalot.knight_parkalot.repository.LicensePlateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LicensePlateService {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final LicensePlateRepository licensePlateRepository;
    private final LicensePlateMapper licensePlateMapper;

    @Autowired
    public LicensePlateService(LicensePlateRepository repository, LicensePlateMapper licensePlateMapper) {
        this.licensePlateRepository = repository;
        this.licensePlateMapper = licensePlateMapper;
    }

    public LicensePlate getLicensePlateNumber(LicensePlateDto licensePlateDto) {
        logger.info("getLicensePlate called");
        Optional<LicensePlate> licensePlateOptional = licensePlateRepository.findById(licensePlateDto.getNumber());
        return licensePlateOptional.orElseGet(() -> licensePlateRepository.save(licensePlateMapper.toEntity(licensePlateDto)));
    }
}
