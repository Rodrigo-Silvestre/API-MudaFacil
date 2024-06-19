package br.com.pucgo.mudafacil.entity;

import java.time.LocalDate;

public record ServicesDTO(String id
        , LocalDate date, Integer value_in_cents
        , UserDTO user
        , VehicleDTO vehicle
        , AddressDTO address_source
        , AddressDTO address_target) {

    public ServicesDTO(Services services) {
        this(services.getId()
                , services.getDate()
                , services.getValue_in_cents()
                , new UserDTO(services.getUser())
                , new VehicleDTO(services.getVehicle())
                , new AddressDTO(services.getAddress_source())
                , new AddressDTO(services.getAddress_target()));
    }
}