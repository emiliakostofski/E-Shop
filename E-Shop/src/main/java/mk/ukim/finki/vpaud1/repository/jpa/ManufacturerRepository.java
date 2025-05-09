package mk.ukim.finki.vpaud1.repository.jpa;

import mk.ukim.finki.vpaud1.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {


}
