package com.example.demo.dto;


public class AnswerDto {
    public String sino;
    public String R;
    public AnswerDto(String sino, String R){
        this.sino = sino;
        this.R = R;
    }


    public String getSino() {
        return sino;
    }

    public void setSino(String sino) {
        this.sino = sino;
    }

    public String getR() {
        return R;
    }

    public void setR(String r) {
        R = r;
    }
}