/**
 * 
 * I declare that this code was written by me, xandr. 
 * I will not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: xandra
 * Student ID: 22022591
 * Date created: 2024-Nov-06 2:43:35 pm 
 * 
 */
package fyp.admin;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author xandr
 *
 */
@Controller
public class HomeController {
	@Autowired
	private MemberRepository memberRepository;
	

/*	@GetMapping("/")
	public String home() {
		return "index";
	} 
*/
	 @GetMapping("/")
	    public String getLeaderboard(Model model) {
	        List<Member> members = memberRepository.findAll(); // Fetch all members
	        Stream<Member> topMembers = members.stream()
	            .filter(member -> "ROLE_USER".equals(member.getRole())); // Only include ROLE_USER
	        model.addAttribute("topMembers", topMembers.toList()); // Convert stream to list for the model
	        return "index";
	    }
	@GetMapping("/leaderboard")
	public String leaderboard(Model model) {
		List<Member> topMembers = memberRepository.findAll();
	    model.addAttribute("topMembers", topMembers);
		return "leaderboard";
	}
	@GetMapping("/history")
	public String history(){
	    return "history";
	}
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
