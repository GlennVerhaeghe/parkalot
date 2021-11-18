package be.parkalot.knight_parkalot.mapper;

import be.parkalot.knight_parkalot.domain.Name;
import be.parkalot.knight_parkalot.dto.NameDto;
import org.springframework.stereotype.Component;

@Component
public class NameMapper {

    public Name toEntity(NameDto nameDto) {
        return new Name(nameDto.getFirstName(), nameDto.getLastName());
    }

    public NameDto toDto(Name name) {
        return new NameDto(name.getFirstName(), name.getLastName());
    }
}
