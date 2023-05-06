package indep.vafl.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import indep.vafl.datarepo.UserRepository;


@Controller
public class PlayPage {
	
	@Autowired
	UserRepository userRepository;
	
	@Secured("ROLE_USER")
	@GetMapping(value="/playTest/{quizID}")  
    public String playTest(@PathVariable(value = "quizID") Integer quizID, Model model) {  
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Integer userID = userRepository.findByUserName(authentication.getName()).get().getId();
		
		model.addAttribute("quizID", quizID);
		model.addAttribute("userID", userID);
		return "playTest.html";  
    }  

}
