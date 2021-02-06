package hu.bozgab.Service;

import hu.bozgab.Entities.User;
import hu.bozgab.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AuthenticationService")
public class AuthenticationService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new AuthenticationDetails(userName);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
