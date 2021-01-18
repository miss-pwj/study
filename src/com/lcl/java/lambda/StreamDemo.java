package com.lcl.java.lambda;


import java.util.Arrays;
import java.util.List;

class User{
    private int id;
    private String userName;
    private int age;

    public User(int id, String userName, int age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
/**
 * @author lcl
 * @date 2021/1/18 10:23
 * @Description
 */
public class StreamDemo {
    public static void main(String[] args){
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream().filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 24)
                .map(user -> user.getUserName().toUpperCase())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(1)
                .forEach(System.out::println);

        list.stream().filter(user -> user.getId() % 2 == 0)
                .filter(user -> user.getAge() > 24)
                .limit(1)
                .forEach(System.out::println);


    }
}
