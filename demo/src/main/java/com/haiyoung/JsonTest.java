package com.haiyoung;

import com.google.gson.*;

import java.util.*;

/**
 * Created by Haiyoung on 2018/1/29.
 */
public class JsonTest {

    public static void main(String[] args){
        Person p1 = new Person();
        p1.setName("xxx");
        p1.setSex("男");

        Person p2 = new Person();
        p2.setName("yyy");
        p2.setSex("女");

        List<Person> list_1 = new ArrayList<>();
        List<Person> list_2 = new ArrayList<>();

        list_1.add(p1);
        list_1.add(p2);

        list_2.add(p2);
        list_2.add(p1);

        for(Person p:list_1){
            System.out.println(p.toString());
        }

        for(Person p:list_2){
            System.out.println(p.toString());
        }

        Map<String,Object> map_1 = new LinkedHashMap<>();
        Map<String,Object> map_2 = new LinkedHashMap<>();

        map_1.put("AAA","BBB");
//        map_1.put("persons",list_1);
        map_1.put("CCC","BBB");

        map_2.put("CCC","BBB");
        map_2.put("AAA","BBB");
//        map_2.put("persons",list_2);

        String json_1 = JsonUtils.toJson(map_1);
        String json_2 = JsonUtils.toJson(map_2);

        String json1 = "{\"id\":1,\"name\":\"eric\"}";
        String json2 = "{\"name\":\"eric\",\"id\":1}";

        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(json_1);
        JsonParser parser1 = new JsonParser();
        JsonObject obj1 = (JsonObject) parser1.parse(json_2);

        

        System.out.println(obj.equals(obj1));

        Gson gson = new GsonBuilder().create();
        JsonElement e1 = gson.toJsonTree(json_1);
        JsonElement e2 = gson.toJsonTree(json_2);
        System.out.println(e1.equals(e2));


    }
}
