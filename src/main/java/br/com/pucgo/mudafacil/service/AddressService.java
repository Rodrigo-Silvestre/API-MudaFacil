package br.com.pucgo.mudafacil.service;

import br.com.pucgo.mudafacil.entity.Address;
import br.com.pucgo.mudafacil.entity.AddressDTO;
import br.com.pucgo.mudafacil.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO register(Address address) {
        address.setActive(true);
        return new AddressDTO(addressRepository.save(address));
    }

    public List<AddressDTO> getAll() {
        List<Address> addresses = addressRepository.findAllByActiveTrue();
        List<AddressDTO> list = new ArrayList<>();
        for (Address address : addresses) {
            list.add(new AddressDTO(address));
        }
        return list;
    }

    public AddressDTO getById(String id) {
        Optional<Address> optionalAddress = addressRepository.findByIdAndActiveTrue(id);
        if (optionalAddress.isPresent()) {
            return new AddressDTO(optionalAddress.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public AddressDTO update(Address address) {
        Optional<Address> optionalAddress = addressRepository.findByIdAndActiveTrue(address.getId());
        if (optionalAddress.isPresent()) {
            Address address1 = optionalAddress.get();
            address1.setCountry(address.getCountry());
            address1.setCity(address.getCity());
            address1.setState(address.getState());
            address1.setNeighborhood(address.getNeighborhood());
            address1.setStreet(address.getStreet());
            address1.setBlock(address.getBlock());
            address1.setBatch(address.getBatch());
            return new AddressDTO(address1);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<Address> optionalAddress = addressRepository.findByIdAndActiveTrue(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setActive(false);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
