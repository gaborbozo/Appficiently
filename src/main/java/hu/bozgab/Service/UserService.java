package hu.bozgab.Service;

import hu.bozgab.Entity.User;
import hu.bozgab.Repository.UserRepository;
import hu.bozgab.Service.Interface.UserIdentify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserIdentify, org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User findByName(String name) { return userRepository.findByName(name); }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByName(userName);
        if(user == null) { throw new UsernameNotFoundException(userName); }

        return new UserDetailsService(user);
    }
}
