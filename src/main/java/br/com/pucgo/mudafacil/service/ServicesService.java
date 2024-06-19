package br.com.pucgo.mudafacil.service;

import br.com.pucgo.mudafacil.entity.ServicesDTO;
import br.com.pucgo.mudafacil.entity.Services;
import br.com.pucgo.mudafacil.repository.ServicesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ServicesService {

    @Autowired
    private ServicesRepository serviceRepository;

    public ServicesDTO register(Services services) {
        services.setActive(true);
        return new ServicesDTO(serviceRepository.save(services));
    }

    public List<ServicesDTO> getAll() {
        List<Services> services = serviceRepository.findAllByActiveTrue();
        List<ServicesDTO> list = new ArrayList<>();
        for (Services services1 : services) {
            list.add(new ServicesDTO(services1));
        }
        return list;
    }

    public ServicesDTO getById(String id) {
        Optional<Services> optionalServices = serviceRepository.findByIdAndActiveTrue(id);
        if (optionalServices.isPresent()) {
            return new ServicesDTO(optionalServices.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public ServicesDTO update(Services services) {
        Optional<Services> optionalServices = serviceRepository.findByIdAndActiveTrue(services.getId());
        if (optionalServices.isPresent()) {
            Services services1 = optionalServices.get();
            services1.setDate(services.getDate());
            services1.setValue_in_cents(services.getValue_in_cents());
            services1.setUser(services.getUser());
            services1.setVehicle(services.getVehicle());
            services1.setAddress_source(services.getAddress_source());
            services1.setAddress_target(services.getAddress_target());
            return new ServicesDTO(services1);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public void delete(String id) {
        Optional<Services> optionalServices = serviceRepository.findByIdAndActiveTrue(id);
        if (optionalServices.isPresent()) {
            Services services = optionalServices.get();
            services.setActive(false);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
