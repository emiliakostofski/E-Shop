package mk.ukim.finki.vpaud1.service;

import mk.ukim.finki.vpaud1.model.Manufacturer;

import java.util.List;
import java.util.Optional;


public interface ManufacturerService {
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Optional<Manufacturer> save(String name, String address);
    boolean existsById(Long id);
    void deleteById(Long id);
}
