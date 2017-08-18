package com.mark.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mark on 17/6/11.
 */
public class User {

    private String name = "Mark";

    private int age = 18;

    private String[] favs = {"吃饭", "睡觉", "打豆豆"};

    private List<Integer> soce = Arrays.asList(new Integer[]{99, 98, 97});

    private Map<String, String> map = new HashMap<String, String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getFavs() {
        return favs;
    }

    public void setFavs(String[] favs) {
        this.favs = favs;
    }

    public List<Integer> getSoce() {
        return soce;
    }

    public void setSoce(List<Integer> soce) {
        this.soce = soce;
    }

    public User() {

    }

    public Map<String, String> getMap() {
        map.put("name", "mark");
        map.put("name.nickname", "earth");
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", favs=" + Arrays.toString(favs) +
                ", soce=" + soce +
                ", map=" + map +
                '}';
    }
}
