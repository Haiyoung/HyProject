package com.haiyoung.controller;

import com.haiyoung.model.Person;
import com.haiyoung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Haiyoung on 2018/5/20.
 */
@RestController
public class RestTest {

    @Autowired
    private PersonService personService;

    @RequestMapping("/person")
    public Person getPerson(HttpServletRequest request,
           @RequestParam(value = "id") Integer id){
        return personService.getPerson(id);
    }

    @RequestMapping("/person/insert")
    public Person insert(@RequestParam(value = "name") String name,
                         @RequestParam(value = "age") Integer age,
                         @RequestParam(value = "description") String description){
        return personService.insert(name,age,description);
    }
}
