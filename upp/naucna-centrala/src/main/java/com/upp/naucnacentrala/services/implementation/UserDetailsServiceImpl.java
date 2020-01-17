package com.upp.naucnacentrala.services.implementation;

import com.upp.naucnacentrala.model.User;
import com.upp.naucnacentrala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User u = userRepository.findByUsername(username);


        if (u == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

        } else {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(u.getRole().name());
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(grantedAuthority);

            return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(),
                    authorities);
        }

    }

}
