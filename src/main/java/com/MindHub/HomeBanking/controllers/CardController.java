package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.CardRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepositories cardRepositories;

    @Autowired
    private ClientRepositories clientRepositories;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<String> createCard(
            @RequestParam CardType type,
            @RequestParam ColorType colorType,
            Authentication authentication){

        Client client = clientRepositories.findByEmail(authentication.getName());

        long colortype = client.getCards().stream()
                .filter(card -> card.getColor() == colorType && card.getType() == type)
                .count();
        long color = client.getCards().stream().filter(card -> card.getColor() == colorType).count();
        long types = client.getCards().stream().filter(card -> card.getType() == type).count();

        if(client.getCards().size() >= 6){
            return new ResponseEntity<>("Se alcanzo limite de tarjetas que puedes tener", HttpStatus.FORBIDDEN);
        }
        if(types >= 3){
            return new ResponseEntity<>("It cannot have more than 3 cards."+ type, HttpStatus.FORBIDDEN);
        }
        if (colortype >=2){
            return new ResponseEntity<>("It cannot have more cards of this color."+ color,HttpStatus.FORBIDDEN);
        }
        if (colortype >= 1){
            return new ResponseEntity<>("It cannot have more cards of this color"+ color + "and this type"+ type, HttpStatus.FORBIDDEN);
        }
        if (client.getCards().size()>=6){
            return new ResponseEntity<>(
                    "Exceeded the number of cards it can have; its limit is 6.", HttpStatus.FORBIDDEN);
        }

        String number = getCardNumber();
        int cvv = (int)(Math.random() * 999 + 100);

        Card card = new Card(client.getName(),type,colorType,number,cvv, LocalDate.now(),LocalDate.now().plusYears(6));
        client.addCard(card);
        cardRepositories.save(card);

        return new ResponseEntity<>("Nueva tarjeta creada", HttpStatus.CREATED);
    }

    private String getCardNumber(){
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i<4; i++){
            int seccionDigitos = (int) (Math.random() * 9000 + 1000);
            cardNumber.append(seccionDigitos).append("-");
        }
        return cardNumber.substring(0,cardNumber.length()-1);
    }
}
