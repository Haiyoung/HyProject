package com.haiyoung.service;

import com.haiyoung.mapper.PersonMapper;
import com.haiyoung.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Haiyoung on 2018/5/20.
 */
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public Person getPerson(Integer id){
        return personMapper.selectByPrimaryKey(id);
    }

    public Person insert(String name, Integer age, String description){
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setDescription(description);
        personMapper.insert(person);

        return person;
    }

}

