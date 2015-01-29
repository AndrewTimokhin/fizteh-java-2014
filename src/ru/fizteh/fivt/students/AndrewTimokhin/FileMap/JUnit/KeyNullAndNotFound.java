package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

public class KeyNullAndNotFound extends Exception {
    private String description;

    public KeyNullAndNotFound(String description) {
        this.description = description;
    }

    public String toString() {
        return "key was not found because it is null!";
    }
}
