package br.com.pucgo.mudafacil.repository;

import br.com.pucgo.mudafacil.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {

    List<Address> findAllByActiveTrue();

    Optional<Address> findByIdAndActiveTrue(String id);
}
