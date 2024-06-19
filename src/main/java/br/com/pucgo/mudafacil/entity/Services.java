package br.com.pucgo.mudafacil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Services implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDate date;

    private Integer value_in_cents;

    @ManyToOne
    private User user;

    @ManyToOne
    //@JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    //@JoinColumn(name = "address_source_id")
    private Address address_source;

    @ManyToOne
    //@JoinColumn(name = "address_target_id")
    private Address address_target;

    private Boolean active;

    public Services(ServicesDTO serviceDTO) {
        this.id = serviceDTO.id();
        this.date = serviceDTO.date();
        this.value_in_cents = serviceDTO.value_in_cents();
        this.user = new User(serviceDTO.user());
        this.vehicle = new Vehicle(serviceDTO.vehicle());
        this.address_source = new Address(serviceDTO.address_source());
        this.address_target = new Address(serviceDTO.address_target());
    }
}