package mk.ukim.finki.vpaud1.service.impl;

import mk.ukim.finki.vpaud1.model.Manufacturer;
import mk.ukim.finki.vpaud1.repository.impl.InMemoryManufacturerRepository;
import mk.ukim.finki.vpaud1.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.vpaud1.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public boolean existsById(Long id) {
        return this.manufacturerRepository.existsById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name, address)));
    }

    @Override
    public void deleteById(Long id) {
         this.manufacturerRepository.deleteById(id);
    }

//    @Override
//    public boolean existsById(Long id) {
//        return this.manufacturerRepository.existsById(id);
//    } ne znam za so e ova ama kje pobaram

}
