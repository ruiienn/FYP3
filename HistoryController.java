package fyp.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository; // Inject the repository

    @GetMapping("/history/{memberId}")
    public String viewHistory(@PathVariable("memberId") Integer memberId, Model model) {
        // Fetch history records for the member, ordered by timestamp descending
        List<History> historyList = historyRepository.findByMemberIdOrderByTimestampDesc(memberId);
        model.addAttribute("historyList", historyList);
        return "points_history";
    }
}

