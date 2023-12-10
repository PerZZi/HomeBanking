package com.MindHub.HomeBanking.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String name;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Account> acounts = new ArrayList<>();


    public Client() {
    }

    public Client(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAcounts() {
        return acounts;
    }

    public void addAcount(Account account){
        account.setClient(this);
        this.acounts.add(account);
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
