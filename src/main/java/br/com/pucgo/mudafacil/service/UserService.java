package br.com.pucgo.mudafacil.service;

import br.com.pucgo.mudafacil.entity.User;
import br.com.pucgo.mudafacil.entity.UserDTO;
import br.com.pucgo.mudafacil.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;
    private Random random = new SecureRandom();

    public UserDTO login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmailAndActiveTrue(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return new UserDTO(user);
            } else {
                throw new IllegalArgumentException();
            }
        }
        throw new EntityNotFoundException();
    }

    public UserDTO register(User user) {
        user.setActive(true);
        return new UserDTO(userRepository.save(user));
    }

    @Transactional
    public void passwordRecovery(String email) {
        String newPassword = generateRandomPassword();
        User user = new User(getByEmail(email));
        user.setActive(true);
        user.setPassword(newPassword);
        update(user);
        emailService.sendEmail(email, "Recuperação de senha.",
                "Olá,\n\n" +
                        "Você solicitou a recuperação de sua senha. Sua nova senha é: " + newPassword +
                        "\nPor favor, faça login com sua nova senha e altere-a imediatamente para garantir a segurança da sua conta.\n\n" +
                        "Se você não solicitou a recuperação de senha, por favor, entre em contato com nosso suporte imediatamente.\n\n" +
                        "Atenciosamente,\n" +
                        "Equipe de Suporte");
    }

    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAllByActiveTrue();
        List<UserDTO> list = new ArrayList<>();
        for (User user : users) {
            list.add(new UserDTO(user));
        }
        return list;
    }

    public UserDTO getById(String id) {
        Optional<User> optionalUser = userRepository.findAllByIdAndActiveTrue(id);
        if (optionalUser.isPresent()) {
            return new UserDTO(optionalUser.get());
        }
        throw new EntityNotFoundException();
    }

    public List<UserDTO> getByName(String name) {
        List<User> users = userRepository.findAllByNameContainingIgnoreCaseAndActiveTrue(name);
        List<UserDTO> list = new ArrayList<>();
        for (User user : users) {
            list.add(new UserDTO(user));
        }
        return list;
    }

    public UserDTO getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndActiveTrue(email);
        if (optionalUser.isPresent()) {
            return new UserDTO(optionalUser.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public UserDTO update(User user) {
        Optional<User> optionalUser = userRepository.findAllByIdAndActiveTrue(user.getId());
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setPassword(user.getPassword());
            return new UserDTO(user1);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<User> optionalUser = userRepository.findAllByIdAndActiveTrue(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
