package main;

import main.model.Case;
import main.model.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TODOListController {

    @Autowired
    private CaseRepository caseRepository;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public List<Case> list() {
        Iterable<Case> iterable = caseRepository.findAll();
        ArrayList<Case> list = new ArrayList<>();
        for (Case cas : iterable) {
            list.add(cas);
        }
        return list;
    }

    @RequestMapping(value = "/list/", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> add(@RequestParam String name) {
        Case response = caseRepository.save(new Case(name));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody Case cas) {
        Case response = caseRepository.save(cas);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public int remove(@PathVariable int id) {
        caseRepository.deleteById(id);
        return id;
    }
}
