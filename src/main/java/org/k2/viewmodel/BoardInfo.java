
package org.k2.viewmodel;

public class BoardInfo {
    private String name;
    private boolean result;
    private String status;

    public BoardInfo(String name, boolean result, String status) {
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