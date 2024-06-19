package br.com.pucgo.mudafacil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String country;

    private String city;

    private String state;

    private String neighborhood;

    private String street;

    private String block;

    private String batch;

    private Boolean active;

    public Address(AddressDTO addressDTO) {
        this.id = addressDTO.id();
        this.country = addressDTO.country();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.neighborhood = addressDTO.neighborhood();
        this.street = addressDTO.street();
        this.block = addressDTO.block();
        this.batch = addressDTO.batch();
    }
}
