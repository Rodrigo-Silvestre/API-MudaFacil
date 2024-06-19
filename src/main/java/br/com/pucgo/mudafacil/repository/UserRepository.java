package br.com.pucgo.mudafacil.repository;

import br.com.pucgo.mudafacil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByActiveTrue();

    Optional<User> findAllByIdAndActiveTrue(String id);

    List<User> findAllByNameContainingIgnoreCaseAndActiveTrue(String name);

    Optional<User> findByEmailAndActiveTrue(String email);
}
