package com.qaprosoft.carina.demo.exeptions;

public class ListIsEmptyException extends Exception{
    public ListIsEmptyException() {
        this("ListIsEmpty");
    }

    public ListIsEmptyException(String message) {
        super(message);
    }
}
