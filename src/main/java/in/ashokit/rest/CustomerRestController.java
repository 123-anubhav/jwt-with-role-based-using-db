package in.ashokit.rest;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepo;
import in.ashokit.service.JwtService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerRepo crepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwt;

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to ashokit";
	}
	
	@GetMapping("/admin/adminRoleWelcome")
	public String adminRoleWelcome() {
		return "welcome adminRoleWelcome";
	}
	
	

	@PostMapping("/login")
	public String loginCheck(@RequestBody Customer c) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(c.getUname(), c.getPwd());
		try {
			Authentication authenticate = authManager.authenticate(token);
			/*if (authenticate.isAuthenticated()) {
				// Optionally return a JWT token or user info
				Customer customer = crepo.findByUname(c.getUname());
				
				//System.out.println(customer);
				
				// Generate JWT token
				 String jwtToken = jwt.generateToken(customer.getUname(), customer.getRoles());
				// return new ResponseEntity<>(jwtToken, HttpStatus.OK);
				return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
			}
			return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
		}*
		
		/*/
			
			 // If authentication succeeds, generate a token
		    if (authenticate.isAuthenticated()) {
		        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
		        Set<String> roles = userDetails.getAuthorities().stream()
		            .map(GrantedAuthority::getAuthority)
		            .collect(Collectors.toSet());

		        return jwt.generateToken(userDetails.getUsername(), roles);
		    } else {
		        throw new BadCredentialsException("Invalid credentials");
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}
		// return new ResponseEntity<>("Authentication failed",
		// HttpStatus.UNAUTHORIZED);
		return "fuckc.,s";
	}

	@PostMapping("/register")
	public String registerCustomer(@RequestBody Customer customer) {
		// Check for duplicate username logic here if needed

		// Encode the password before saving
		String encodedPwd = pwdEncoder.encode(customer.getPwd());
		customer.setPwd(encodedPwd);

		crepo.save(customer);

		return "User registered";
	}

}
