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

@Entity(name = "vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String type_of_vehicle;

    private Integer load_in_kilograms;

    private Boolean active;

    public Vehicle(VehicleDTO vehicleDTO) {
        this.id = vehicleDTO.id();
        this.name = vehicleDTO.name();
        this.type_of_vehicle = vehicleDTO.type_of_vehicle();
        this.load_in_kilograms = vehicleDTO.load_in_kilograms();
    }
}
