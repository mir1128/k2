package org.k2.viewmodel;

public class MoveInfo {
    private String name;
    private boolean result;
    private String status;
    private String message;
    private int score;

    public MoveInfo(String name, boolean result, String status, String message, int score) {
        this.name = name;
        this.result = result;
        this.status = status;
        this.message = message;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
