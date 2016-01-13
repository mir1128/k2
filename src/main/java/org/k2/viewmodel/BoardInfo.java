
package org.k2.viewmodel;

public class BoardInfo {
    private String name;
    private boolean result;
    private String status;

    public BoardInfo(String name, boolean result, String status) {
        this.name = name;
        this.result = result;
        this.status = status.substring(1, status.length()-1);
    }

    public String getName() {
        return name;
    }

    public boolean getResult() {
        return result;
    }

    public String getStatus() {
        return status;
    }
}