package com.company.Tasca3;

import java.nio.charset.StandardCharsets;

public class Llista {


    private String[] paraules = {"PROGRAMACIO", "LINUX","UBUNTU","ANDROID","JAVA","SMARTPHONE"};

    public byte[] getParaula() {

        int num = (int)((Math.random() * paraules.length)-1);
        byte[] paraula= paraules[num].getBytes();

        return paraula;
    }
}
