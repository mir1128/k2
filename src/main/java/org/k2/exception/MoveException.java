package org.k2.exception;

import org.k2.viewmodel.MoveInfo;

public class MoveException extends Exception {
    private String name;
    private String status;

    public MoveException(String message, String name, String status) {
        super(message);
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
