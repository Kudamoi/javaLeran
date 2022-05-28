package main;

import main.model.Case;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Case> iterable = caseRepository.findAll();
        ArrayList<Case> list = new ArrayList<>();
        for (Case cas : iterable) {
            list.add(cas);
        }

        model.addAttribute("cases", list);
        model.addAttribute("casesS", list.size());
        return "index";
    }
}
