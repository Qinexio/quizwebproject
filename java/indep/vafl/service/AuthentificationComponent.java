package indep.vafl.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import indep.vafl.datarepo.CredidentialRepository;
import indep.vafl.datarepo.UserRepository;
import indep.vafl.entity.Credidential;
import indep.vafl.entity.User;

@Component
public class AuthentificationComponent implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CredidentialRepository credRepository;

	@Autowired
	private PassService passService;
	
	@Value("${admin.name}")
	private String adminName;

	@Value("${admin.pass}")
	private String adminPass;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String userName = auth.getName();
		String passWord = (String) auth.getCredentials();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if(userName.equals(adminName) && passWord.equals(adminPass))
		{
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new UsernamePasswordAuthenticationToken(userName, passWord, grantedAuthorities);
		}
		
		Optional<User> user = userRepository.findByUserName(userName);

		if (!user.isPresent()) {
			throw new BadCredentialsException("Nume de utilizator inexistent.");
		}
		
		if (!user.get().isUserIsVerified()) {
			throw new BadCredentialsException("Utilizatorul nu este validat.");
		}

		Optional<Credidential> cred = credRepository.findByCredUserId(user.get().getId());

		passWord = passService.encryptPass(passWord, cred.get().getCredSalt());

		if (!passWord.equals(cred.get().getCredPassword())) {
			throw new BadCredentialsException("Parola gresita.");
		}


		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new UsernamePasswordAuthenticationToken(userName, passWord, grantedAuthorities);

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}

}
