package be.parkalot.knight_parkalot.dto;

public class LicensePlateDto {

    private final String number;
    private final String countryCode;

    public LicensePlateDto(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
