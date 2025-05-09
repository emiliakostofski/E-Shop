package mk.ukim.finki.vpaud1.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class UsernameAlreadyExistException extends RuntimeException{

    public UsernameAlreadyExistException(String username) {
        super (String.format("User already exists with username: %s" + username));
    }
}
