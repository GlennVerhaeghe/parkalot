package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.CreateNameDto;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {

    public Name toEntity(CreateNameDto nameDto) {
        return new Name(nameDto.getFirstName(), nameDto.getLastName());
    }
}
