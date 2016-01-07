package org.k2.model;

public enum MoveDirection {
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right");

    private String name;

    MoveDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
