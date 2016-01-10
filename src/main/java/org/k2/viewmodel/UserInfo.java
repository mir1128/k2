package org.k2.viewmodel;

public class UserInfo {
    private String name;
    private int score;

    public UserInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
