package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.data.UserRepository;
import tacos.model.User;

/**
 * @author pi
 * @date 2020/8/18 15:21:09
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + s +"' not found");
    }
}
