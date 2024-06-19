package br.com.pucgo.mudafacil.repository;

import br.com.pucgo.mudafacil.entity.Services;
import br.com.pucgo.mudafacil.entity.ServicesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, String> {

    List<Services> findAllByActiveTrue();

    Optional<Services> findByIdAndActiveTrue(String id);
}
