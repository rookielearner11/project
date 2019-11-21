package com.example.clinic;

import java.util.ArrayList;

public class UserInformation {
    private String name;
    private String email;
    private String role;
    private String phone;
    private String sex;
    private String licensed;
    private String company;
    private String address;
    ArrayList<String> ava = new ArrayList<String>();





    public UserInformation(){

    }
    
    public UserInformation(UserInformation a){
//        this.name = a.name;
//        this.email = a.email;
//        this.role = a.role;
        this.phone = a.phone;
        this.sex = a.sex;
        this.licensed = a.licensed;
        this.company = a.company;
        this.address = a.address;
        this.ava = a.ava;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) { this.sex = sex; }

    public String getLicensed() {
        return licensed;
    }

    public void setLicensed(String licensed) {
        this.licensed = licensed;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvailability(String ava) {this.ava.add(ava);}

    public String getAvailability() {
        String res = "Availabilities: \n";
        for (String item : ava){
            res = res + item +"\n";
        }
        return res;
    }


}
