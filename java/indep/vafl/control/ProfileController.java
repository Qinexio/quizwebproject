package indep.vafl.control;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import indep.vafl.datarepo.ProfileRepository;
import indep.vafl.datarepo.UserRepository;
import indep.vafl.entity.Profile;
import indep.vafl.utility.EntityNotFoundException;

@RestController
public class ProfileController {
	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	UserRepository userRepository;

	@Secured("ROLE_ADMIN")
	@GetMapping("/user/{userID}/profile")
	public Optional<Profile> getByUserID(@PathVariable(value = "userID") Integer userID) {
		return profileRepository.findByProfileUserId(userID);

	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/profileHold/{profileID}")
	public Profile holdBack(@PathVariable(value = "profileID") Integer profileID) {

		return profileRepository.findById(profileID).map(profile -> {
			profile.setProfileIsRepeating(true);
			return profileRepository.save(profile);
		}).orElseThrow(() -> new EntityNotFoundException("credID " + profileID + "not found"));
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/profileForward/{profileID}")
	public Profile pushForward(@PathVariable(value = "profileID") Integer profileID) {

		return profileRepository.findById(profileID).map(profile -> {
			profile.setProfileIsRepeating(false);
			return profileRepository.save(profile);
		}).orElseThrow(() -> new EntityNotFoundException("credID " + profileID + "not found"));
	}
}
