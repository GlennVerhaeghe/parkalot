package be.parkalot.knight_parkalot.dto;

public class PostalCodeDto {

    private final int code;
    private final String city;

    public PostalCodeDto(int code, String city) {
        this.code = code;
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }
}
