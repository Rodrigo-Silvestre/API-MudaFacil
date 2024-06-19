package br.com.pucgo.mudafacil.repository;

import br.com.pucgo.mudafacil.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findAllByActiveTrue();

    Optional<Vehicle> findByIdAndActiveTrue(String id);
}
