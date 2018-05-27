package com.haiyoung.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyoung.model.Person;
import com.haiyoung.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping("/allPersons")
    public List<Person> getPerson(HttpServletRequest request){
        return personService.getAllPersons();
    }

    @RequestMapping("/page/allPersons")
    public PageInfo<List<Person>> getPersonByPage(HttpServletRequest request,
           @RequestParam(value = "pageNum") Integer pageNum,
           @RequestParam(value = "pageSize") Integer pageSize){
//        PageHelper.startPage(1, 5);
        PageHelper.startPage(pageNum, pageSize);
        //紧跟着的第一个select方法会被分页
        List<Person> persons = personService.getAllPersons();
        PageInfo page = new PageInfo(persons);
        return page;
    }

    @RequestMapping("/person/insert")
    public Person insert(@RequestParam(value = "name") String name,
                         @RequestParam(value = "age") Integer age,
                         @RequestParam(value = "description") String description){
        return personService.insert(name,age,description);
    }
}
