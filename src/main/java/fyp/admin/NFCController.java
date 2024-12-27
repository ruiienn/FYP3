package fyp.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nfc")
public class NFCController {

    @Autowired
    private MemberDetailsService memberService; // Handles member operations

    /**
     * Process NFC scan and update user points
     * @param id User ID extracted from NFC tag URL
     * @return Response indicating success or failure
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> processNFCScan(@PathVariable Long id) {
        Optional<Member> optionalMember = memberService.findById(id);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setPoints(member.getPoints() + 10); // Increment points
            memberService.save(member); // Save updated member
            return ResponseEntity.ok("Points updated successfully for user ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member not found!");
        }
    }
}
