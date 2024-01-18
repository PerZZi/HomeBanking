package com.MindHub.HomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardholder;
    private CardType type;
    private ColorType colorType;
    private String number;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private boolean state = true;
    @ManyToOne
    private Client client;

    public Card() {
    }

    public Card(String cardholder, CardType type, ColorType colorType, String number, Integer cvv, LocalDate fromDate, LocalDate thruDate, boolean state) {
        this.cardholder = cardholder;
        this.type = type;
        this.colorType = colorType;
        this.number = number;
        this.cvv = cvv;
        this.fromDate = fromDate;
        this.thruDate = thruDate;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public ColorType getColor() {
        return colorType;
    }

    public void setColor(ColorType colorType) {
        this.colorType = colorType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardholder='" + cardholder + '\'' +
                ", type=" + type +
                ", colorType=" + colorType +
                ", number='" + number + '\'' +
                ", cvv=" + cvv +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                ", state=" + state +
                '}';
    }
}
