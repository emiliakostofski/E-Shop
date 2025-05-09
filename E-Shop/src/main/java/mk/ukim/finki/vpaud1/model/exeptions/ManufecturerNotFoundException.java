package mk.ukim.finki.vpaud1.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufecturerNotFoundException extends RuntimeException{
    public ManufecturerNotFoundException(Long id){
        super(String.format("Manufecturer with id %d was not found", id));
    }

}
