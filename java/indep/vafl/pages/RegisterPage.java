package indep.vafl.pages;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import indep.vafl.datarepo.CredidentialRepository;
import indep.vafl.datarepo.ProfileRepository;
import indep.vafl.datarepo.UserRepository;
import indep.vafl.entity.Credidential;
import indep.vafl.entity.Profile;
import indep.vafl.entity.User;
import indep.vafl.service.EmailService;
import indep.vafl.service.PassService;
import indep.vafl.utility.Register;

@Controller
public class RegisterPage {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CredidentialRepository credRepository;

	@Autowired
	ProfileRepository profRepository;

	@Autowired
	PassService passService;
	
	@Autowired
	EmailService emailService;

	@Value("${admin.name}")
	private String adminName;

	@GetMapping(value = "/register")
	public String registerForm(Model model) {

		model.addAttribute("registerObj", new Register());

		return "registerForm";
	}

	@PostMapping(value = "/register")
	public String formSubmit(@Valid @ModelAttribute(value = "registerObj") Register regResult,
			BindingResult bindingResult, Model model) {

		if (userRepository.existsByUserName(regResult.getRegisterUser())
				|| regResult.getRegisterUser().equals(adminName)) {
			bindingResult.rejectValue("registerUser", null, "Este deja un utilizator cu acelasi nume");
		}

		if (userRepository.existsByUserMail(regResult.getRegisterMail())) {
			bindingResult.rejectValue("registerMail", null, "Este deja un utilizator cu aceasta adresa");
		}

		if (!regResult.getRegisterPass().equals(regResult.getRegisterConfirmPass())) {
			bindingResult.rejectValue("registerConfirmPass", null, "Parolele nu sunt identice");
		}

		if (regResult.getRegisterAge() < 5 || regResult.getRegisterAge() > 30) {
			bindingResult.rejectValue("registerAge", null, "Varsta invalida");
		}
		
		if (bindingResult.hasErrors()) {
			return "registerForm";
		}

		User newUser = new User();
		newUser.setUserName(regResult.getRegisterUser());
		newUser.setUserMail(regResult.getRegisterMail());
		newUser.setUserConfirmation(RandomStringUtils.randomAlphabetic(16));
		newUser.setUserIsVerified(false);

		newUser = userRepository.save(newUser);

		Credidential newCred = new Credidential();

		userRepository.findById(newUser.getId()).map(user -> {
			newCred.setCredPassword(regResult.getRegisterPass());
			newCred.setCredSalt(passService.generateSalt());
			newCred.setCredPassword(passService.encryptPass(newCred.getCredPassword(), newCred.getCredSalt()));
			newCred.setCredUser(user);
			return credRepository.save(newCred);
		});

		Profile newProf = new Profile();

		userRepository.findById(newUser.getId()).map(user -> {
			newProf.setProfileAge(regResult.getRegisterAge());
			newProf.setProfileClass(regResult.getRegisterClass());
			newProf.setProfileGender(regResult.getRegisterGender());
			newProf.setProfileIsRepeating(false);
			newProf.setProfileName(regResult.getRegisterName());
			newProf.setProfileUser(user);
			return profRepository.save(newProf);
		});
		
		emailService.sendConfirmation(newUser.getUserConfirmation(), newUser.getUserMail());

		return "redirect:/home?success";
	}

}
