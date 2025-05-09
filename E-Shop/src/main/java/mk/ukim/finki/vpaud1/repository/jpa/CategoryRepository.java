package mk.ukim.finki.vpaud1.repository.jpa;

import mk.ukim.finki.vpaud1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);
}
