package org.acme.getting.started;

public class Greeting {

    private String who;

    private int age;

    public Greeting() {
    }

    public Greeting(String who, int age) {
        this.who = who;
        this.age = age;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
