package mk.ukim.finki.vpaud1.service.impl;


import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.model.exeptions.InvalidArgumentException;
import mk.ukim.finki.vpaud1.model.exeptions.InvalidUserCredentialsExeption;
import mk.ukim.finki.vpaud1.model.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.vpaud1.model.exeptions.UsernameAlreadyExistException;
import mk.ukim.finki.vpaud1.repository.jpa.UserRepository;
import mk.ukim.finki.vpaud1.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || password==null || username.isEmpty() || password.isEmpty())
        {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsExeption::new);
    }
}
