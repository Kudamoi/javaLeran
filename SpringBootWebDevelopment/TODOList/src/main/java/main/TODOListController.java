package main;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TODOListController {

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public List<Case> list()
    {
        return Storage.getAllCases();
    }

    @RequestMapping(value = "/list/", method = RequestMethod.PUT)
    public Case add(Case cas) {
        return Storage.add(cas);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    public int remove(@PathVariable int id) {
       return Storage.delete(id);
    }
}
