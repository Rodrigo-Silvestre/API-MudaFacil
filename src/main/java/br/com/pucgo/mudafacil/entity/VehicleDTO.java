package br.com.pucgo.mudafacil.entity;

public record VehicleDTO(String id, String name, String type_of_vehicle, Integer load_in_kilograms) {

    public VehicleDTO(Vehicle vehicle) {
        this(vehicle.getId(), vehicle.getName(), vehicle.getType_of_vehicle(), vehicle.getLoad_in_kilograms());
    }
}
