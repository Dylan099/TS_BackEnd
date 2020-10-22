package com.example.demo.dto;

public enum Estatus {

    ACTIVE(1), INACTIVE(0);

    Estatus(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }
}
