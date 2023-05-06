package indep.vafl.pages;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreatePage {

	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/createTest", method=RequestMethod.GET)  
    public String create() {  
        return "createTest.html";  
    }  

}
