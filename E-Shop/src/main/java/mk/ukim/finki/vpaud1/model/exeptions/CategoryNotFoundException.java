package mk.ukim.finki.vpaud1.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long id){
        super(String.format("Category with id %d was not found!", id));
    }

}
