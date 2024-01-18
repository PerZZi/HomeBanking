package com.MindHub.HomeBanking.dto;

import com.MindHub.HomeBanking.models.Card;
import com.MindHub.HomeBanking.models.CardType;
import com.MindHub.HomeBanking.models.ColorType;

import java.time.LocalDate;

public class CardDTO {

    private Long id;
    private String cardholder;
    private CardType type;
    private ColorType colorType;
    private String number;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private boolean state;

    public CardDTO(Card card) {
        id = card.getId();
        cardholder = card.getCardholder();
        type = card.getType();
        colorType = card.getColor();
        number = card.getNumber();
        cvv = card.getCvv();
        fromDate = card.getFromDate();
        thruDate = card.getThruDate();
        state = card.isState();
    }

    public Long getId() {
        return id;
    }

    public String getCardholder() {
        return cardholder;
    }

    public CardType getType() {
        return type;
    }

    public ColorType getColorType() {
        return colorType;
    }

    public String getNumber() {
        return number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public boolean isState() {
        return state;
    }
}
