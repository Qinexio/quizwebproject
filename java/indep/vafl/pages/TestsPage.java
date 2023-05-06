package indep.vafl.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import indep.vafl.datarepo.QuizRepository;
import indep.vafl.entity.Quiz;

@Controller
public class TestsPage {

	@Autowired
	QuizRepository quizRepository;

	@Secured("ROLE_USER")
	@GetMapping("/showTests")
	public String getTests(@PageableDefault(size = 10) Pageable pageable, Model model) {
		
		Page<Quiz> page = quizRepository.findByQuizValidation(true, pageable);
		model.addAttribute("page", page);
		return "showTests";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/showEditTest")
	public String getTestsAdmin(@PageableDefault(size = 10) Pageable pageable, Model model) {
		
		Page<Quiz> page = quizRepository.findAll(pageable);
		model.addAttribute("page", page);
		return "showTestList";
	}

}