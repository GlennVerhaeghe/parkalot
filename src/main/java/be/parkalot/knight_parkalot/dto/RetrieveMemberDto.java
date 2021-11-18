package be.parkalot.knight_parkalot.dto;

import java.time.LocalDate;

public class RetrieveMemberDto {

    private final int id;
    private final CreateNameDto nameDto;
    private final String licensePlateNumber;
    private final String telephoneNumber;
    private final String email;
    private final LocalDate registrationDate;

    public RetrieveMemberDto(int id, CreateNameDto nameDto, String licensePlateNumber, String telephoneNumber, String email, LocalDate registrationDate) {
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

    public CreateNameDto getNameDto() {
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
