package indep.vafl.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import indep.vafl.datarepo.QuizRepository;
import indep.vafl.entity.Quiz;

@Controller
public class EditPage {
	@Autowired
	QuizRepository quizRepository;
	
	@Secured("ROLE_ADMIN")
	@GetMapping(value="/editTest/{quizID}")  
    public String editTest(@PathVariable(value = "quizID") Integer quizID, Model model) {  
		model.addAttribute("quizID", quizID);
		
		Quiz toSet = quizRepository.findById(quizID).get();
		toSet.setValidation(false);
		quizRepository.save(toSet);
		
		return "editTest.html";  
    }

}

