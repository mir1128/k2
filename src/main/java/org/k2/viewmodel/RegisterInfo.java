package org.k2.viewmodel;

public class RegisterInfo {

    private String name;
    private boolean result;
    private String status;

    public RegisterInfo(String name, boolean result, String status) {
        this.name = name;
        this.result = result;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public boolean isResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }
}
