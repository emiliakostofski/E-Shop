package mk.ukim.finki.vpaud1.web.servlet.rest;

import mk.ukim.finki.vpaud1.model.Manufacturer;
import mk.ukim.finki.vpaud1.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    public final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll()
    {
        return this.manufacturerService.findAll();
    }

    //path variable ili query parametar, razlikata e vo toa sto /{id} e path variabla
    // /manu?id=89 ova e query parametar so key id i vrednost 89
    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id)
    {
        return this.manufacturerService.findById(id)
                .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //ResponseEntity za menadziranje na status kodot
    @PostMapping("/add")
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address)
    {
           return this.manufacturerService.save(name,address)
                   .map(manufacturer -> ResponseEntity.ok().body(manufacturer))
                   .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id)
    {
        if (this.manufacturerService.existsById(id)) {
            this.manufacturerService.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}

