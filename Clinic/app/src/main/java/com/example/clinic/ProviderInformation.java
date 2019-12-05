package com.example.clinic;
import java.util.ArrayList;


public class ProviderInformation{
    private String name;
    private String avas = "";
    public ProviderInformation(){}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvas() { return avas; }
    public void setAvas(String ava) {
        this.avas = this.avas + "\n" +ava;
    }
}