package com.company.Tasca3;

import java.util.*;


public class Imprimeix {

    Map<String, Integer> paraules;


    public Imprimeix() {
        this.paraules = new HashMap<>();
    }

    public void add(String paraula) {
        /*if (!paraules.containsKey(paraula))paraules.put(paraula,1);
        else paraules.put(,paraules.get(paraula)+1);*/
        if (paraules.containsKey(paraula)) paraules.put(paraula,paraules.get(paraula)+1);
        else paraules.put(paraula,1);
    }

    public void printMonitor(){
        System.out.println(paraules.toString());

    }
}

