package com.example.usuario.ad_ejercicios_t4.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ContactJSON {

    @SerializedName("contacts")
    @Expose
    private ArrayList<ContactJSON.Contact> contacts = null;

    public ArrayList<ContactJSON.Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactJSON.Contact> contacts) {
        this.contacts = contacts;
    }


    public class Contact {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private Phone phone;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public Phone getPhone() {
            return phone;
        }
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return getName();
        }

    }

    public class Phone {

        @SerializedName("home")
        @Expose
        private String home;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("work")
        @Expose
        private String work;

        public String getHome() {
            return home;
        }
        public void setHome(String home) {
            this.home = home;
        }
        public String getMobile() {
            return mobile;
        }
        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getWork() {
            return work;
        }
        public void setWork(String work) {
            this.work = work;
        }

        @Override
        public String toString() {
            return getMobile();
        }
    }

}




