package br.com.pucgo.mudafacil.service;

import br.com.pucgo.mudafacil.entity.Vehicle;
import br.com.pucgo.mudafacil.entity.VehicleDTO;
import br.com.pucgo.mudafacil.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleDTO register(Vehicle vehicle) {
        vehicle.setActive(true);
        return new VehicleDTO(vehicleRepository.save(vehicle));
    }

    public List<VehicleDTO> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAllByActiveTrue();
        List<VehicleDTO> list = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            list.add(new VehicleDTO(vehicle));
        }
        return list;
    }

    public VehicleDTO getById(String id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByIdAndActiveTrue(id);
        if (optionalVehicle.isPresent()) {
            return new VehicleDTO(optionalVehicle.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public VehicleDTO update(Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByIdAndActiveTrue(vehicle.getId());
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle1 = optionalVehicle.get();
            vehicle1.setName(vehicle.getName());
            vehicle1.setType_of_vehicle(vehicle.getType_of_vehicle());
            vehicle1.setLoad_in_kilograms(vehicle.getLoad_in_kilograms());
            return new VehicleDTO(vehicle1);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByIdAndActiveTrue(id);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle1 = optionalVehicle.get();
            vehicle1.setActive(false);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
