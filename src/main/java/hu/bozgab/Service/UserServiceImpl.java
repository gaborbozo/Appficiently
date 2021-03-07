package hu.bozgab.Service;

import hu.bozgab.Entity.Role;
import hu.bozgab.Entity.User;
import hu.bozgab.Repository.RoleRepository;
import hu.bozgab.Repository.UserRepository;
import hu.bozgab.Service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByEmail(String email) { return userRepository.findByEmail(email); }

    @Override
    public User findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    //@Qualifier("UserDetailsImpl") - Not required, because the UserDetailsImpl is the only implementation of UserDetails -> Spring knows what to inject
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUsername(userName);
        if (user == null) {
            user = findByEmail(userName);
            if(user == null) throw new UsernameNotFoundException(userName);
        }

        return new UserDetailsImpl(user);
    }

    @Override
    public void registerUser(User user) {
        Role userRole = roleRepository.findByRole("ROLE_USER");

        if(userRole != null){
            user.getRoles().add(userRole);
        } else {
            user.addRoles("ROLE_USER");
        }

        user.setRegistered(new Date());
        userRepository.save(user);
    }
}
