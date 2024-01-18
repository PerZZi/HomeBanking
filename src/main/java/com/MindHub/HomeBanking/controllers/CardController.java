package com.MindHub.HomeBanking.controllers;

import com.MindHub.HomeBanking.models.*;
import com.MindHub.HomeBanking.repositories.CardRepositories;
import com.MindHub.HomeBanking.repositories.ClientRepositories;
import com.MindHub.HomeBanking.service.CardService;
import com.MindHub.HomeBanking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.MindHub.HomeBanking.utils.Utils.GeneratedCardCvv;
import static com.MindHub.HomeBanking.utils.Utils.getCardNumber;


@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @PostMapping("/clients/current/cards")
    public ResponseEntity<String> createCard(
            @RequestParam CardType type,
            @RequestParam ColorType colorType,
            Authentication authentication){

        Client client = clientService.getAuthenticatedClient(authentication.getName());

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
        int cvv = GeneratedCardCvv();

        Card card = new Card(client.getName(),type,colorType,number,cvv, LocalDate.now(),LocalDate.now().plusYears(6), true);
        client.addCard(card);
        cardService.saveCard(card);

        return new ResponseEntity<>("Nueva tarjeta creada", HttpStatus.CREATED);
    }

    @PatchMapping("/clients/current/cards/delete")
    public ResponseEntity<String> deleteCard(@RequestParam Long id,
                                             Authentication authentication){
        Client client = clientService.getAuthenticatedClient(authentication.getName());
        Card card = cardService.findById(id);

        if(card.isState() && card.getClient().getEmail().equals(authentication.getName())){
            cardService.deleteCard(card);
            return new ResponseEntity<>("Tarjeta eliminada", HttpStatus.OK);
        }
        return new ResponseEntity<>("Tu tarjeta ya esta cancelada", HttpStatus.BAD_REQUEST);
    }
}
