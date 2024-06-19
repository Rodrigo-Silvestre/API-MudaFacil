package br.com.pucgo.mudafacil.controller;

import br.com.pucgo.mudafacil.entity.Vehicle;
import br.com.pucgo.mudafacil.entity.VehicleDTO;
import br.com.pucgo.mudafacil.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public ResponseEntity<VehicleDTO> register(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.register(new Vehicle(vehicleDTO)));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<VehicleDTO> getById(@PathVariable @Valid String id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<VehicleDTO> update(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.update(new Vehicle(vehicleDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
