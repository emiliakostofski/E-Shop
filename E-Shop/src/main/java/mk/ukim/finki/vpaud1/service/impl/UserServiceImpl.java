package mk.ukim.finki.vpaud1.service.impl;

import mk.ukim.finki.vpaud1.model.User;
import mk.ukim.finki.vpaud1.model.enumeration.Role;
import mk.ukim.finki.vpaud1.model.exeptions.InvalidArgumentException;
import mk.ukim.finki.vpaud1.model.exeptions.PasswordDoNotMatchException;
import mk.ukim.finki.vpaud1.model.exeptions.UsernameAlreadyExistException;
import mk.ukim.finki.vpaud1.repository.jpa.UserRepository;
import mk.ukim.finki.vpaud1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
           //ova e toj primer so biznis logika i TREBA samo tuka da bide vo ovoj sloj
            if(username==null || password==null || username.isEmpty() || password.isEmpty())
            {
                throw new InvalidArgumentException();
            }
            if (!password.equals(repeatPassword)) {
                throw new PasswordDoNotMatchException();
            }

            if(this.userRepository.findByUsername(username).isPresent())
                throw new  UsernameAlreadyExistException(username);


            User user = new User(username, passwordEncoder.encode(password),  name, surname, role);
            return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException(s));
    }
}
