package be.parkalot.knight_parkalot.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostalCodeDto that = (PostalCodeDto) o;
        return code == that.code && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, city);
    }
}
