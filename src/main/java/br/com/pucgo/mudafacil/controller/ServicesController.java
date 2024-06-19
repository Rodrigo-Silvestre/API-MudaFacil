package br.com.pucgo.mudafacil.controller;

import br.com.pucgo.mudafacil.entity.Services;
import br.com.pucgo.mudafacil.entity.ServicesDTO;
import br.com.pucgo.mudafacil.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("/register")
    public ResponseEntity<ServicesDTO> register(@RequestBody @Valid ServicesDTO servicesDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicesService.register(new Services(servicesDTO)));
    }

    @GetMapping
    public ResponseEntity<List<ServicesDTO>> getAll() {
        return ResponseEntity.ok(servicesService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ServicesDTO> getById(@PathVariable @Valid String id) {
        return ResponseEntity.ok(servicesService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ServicesDTO> update(@RequestBody @Valid ServicesDTO servicesDTO) {
        return ResponseEntity.ok(servicesService.update(new Services(servicesDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        servicesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
