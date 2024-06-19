package br.com.pucgo.mudafacil.entity;

public record UserDTO(String id, String name, String phone, String email, String password) {

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getPassword());
    }
}