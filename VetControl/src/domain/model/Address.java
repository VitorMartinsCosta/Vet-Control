package domain.model;

import java.util.Objects;
import java.util.stream.Stream;

import domain.exceptions.ValidationException;

public class Address {
    private final String street, number, city, state, zipCode, complement;

    public Address(String street, String number, String city, String state, String zipCode, String complement) {
            if(Stream.of(street, number, city, state, zipCode).anyMatch(p -> p == null || p.isBlank())){
                throw new ValidationException("Invalid Address: all fields are mandatory.");
            }
            this.street = street.trim();
            this.number = number.trim();
            this.city = city.trim();
            this.state = state.trim();
            this.zipCode = zipCode.trim();
            this.complement = (complement == null || complement.isBlank()) ? null : complement.trim();
}

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getComplement() {
        return complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, complement, number, state, street, zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Address other = (Address) o;

        return Objects.equals(street, other.street)
            && Objects.equals(number, other.number)
            && Objects.equals(city, other.city)
            && Objects.equals(state, other.state)
            && Objects.equals(zipCode, other.zipCode)
            && Objects.equals(complement, other.complement);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(street)
        .append( ", " )
        .append(number)
        .append(", ")
        .append(city)
        .append(" - ")
        .append(state)
        .append(", ")
        .append(zipCode);

        if (complement != null && !complement.isBlank()) {
            sb.append(", ").append(complement);
        }
            
        return sb.toString();
    }
}
