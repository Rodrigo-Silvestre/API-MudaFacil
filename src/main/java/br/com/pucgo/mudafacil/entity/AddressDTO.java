package br.com.pucgo.mudafacil.entity;

public record AddressDTO(String id, String country, String city, String state, String neighborhood, String street, String block, String batch) {

    public AddressDTO(Address address) {
        this(address.getId(), address.getCountry(), address.getCity(), address.getState(), address.getNeighborhood(), address.getStreet(), address.getBlock(), address.getBatch());
    }
}