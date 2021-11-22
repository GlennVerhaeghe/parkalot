package be.parkalot.knight_parkalot.dto.member;

import be.parkalot.knight_parkalot.dto.NameDto;

import java.time.LocalDate;

public class MemberDetailsDto {

    private final int id;
    private final NameDto nameDto;
    private final String licensePlateNumber;
    private final String telephoneNumber;
    private final String email;
    private final LocalDate registrationDate;

    public MemberDetailsDto(int id, NameDto nameDto, String licensePlateNumber, String telephoneNumber, String email, LocalDate registrationDate) {
        this.id = id;
        this.nameDto = nameDto;
        this.licensePlateNumber = licensePlateNumber;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public NameDto getNameDto() {
        return nameDto;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
