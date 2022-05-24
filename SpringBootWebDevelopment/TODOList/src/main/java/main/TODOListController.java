package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TODOListController {

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public List<Case> list()
    {
        return Storage.getAllCases();
    }

    @RequestMapping(value = "/list/", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> add(@RequestParam String name) {
        return new ResponseEntity<>(Storage.add(new Case(name)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody Case cas) {
        return new ResponseEntity<>(Storage.add(cas), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public int remove(@PathVariable int id) {
       return Storage.delete(id);
    }
}
