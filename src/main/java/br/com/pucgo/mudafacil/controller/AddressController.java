package br.com.pucgo.mudafacil.controller;

import br.com.pucgo.mudafacil.entity.Address;
import br.com.pucgo.mudafacil.entity.AddressDTO;
import br.com.pucgo.mudafacil.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/register")
    public ResponseEntity<AddressDTO> register(@RequestBody @Valid AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.register(new Address(addressDTO)));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AddressDTO> getById(@PathVariable @Valid String id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<AddressDTO> update(@RequestBody @Valid AddressDTO addressDTO) {
        return ResponseEntity.ok(addressService.update(new Address(addressDTO)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid String id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}