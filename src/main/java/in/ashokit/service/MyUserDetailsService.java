package in.ashokit.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo crepo;

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetch customer details from the database
        Customer customer = crepo.findByUname(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Convert customer roles to GrantedAuthority with ROLE_ prefix
        Set<GrantedAuthority> authorities = customer.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Add "ROLE_" prefix here
                .collect(Collectors.toSet());

        return new User(customer.getUname(), customer.getPwd(), authorities);
    }

    
}
