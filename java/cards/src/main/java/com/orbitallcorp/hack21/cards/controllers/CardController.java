package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.callback.SecretKeyCallback;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardServi;

    //Retorna todos os cartões
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @GetMapping
    public ResponseEntity<List<Card>> getCards(){
        return ResponseEntity.ok(cardServi.getCards());
    }

    //Insere um registro na tabela card do banco de dados
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Card setCard(@RequestBody Card card){
        return cardServi.setCard(card);
    }

    //Atualiza um registro existente

    @PutMapping(path = "/cards/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable(name="id") Long id,@RequestBody Card card){
        Card newCard =  cardServi.updateCard(id,card);

       if(newCard==null){
            return new ResponseEntity(newCard,HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(newCard,HttpStatus.OK);
        }
    }

    //Deleta um cartão por id
    @DeleteMapping(path = "/cards/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> deleteCard(@PathVariable(name="id") Long id){
        boolean newCard =  cardServi.deleteCard(id);

        if(newCard){
            return new ResponseEntity("Deletado com sucesso",HttpStatus.OK);
        }else{
            return new ResponseEntity("Não existe um cadastro com esse id",HttpStatus.BAD_REQUEST);
        }
    }

    //Retorna um cartão específico
    @GetMapping(path = "/cards/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity getCard(@PathVariable(name="id") Long id){
        boolean newCard =  cardServi.deleteCard(id);

        if(newCard){
            return new ResponseEntity(newCard,HttpStatus.OK);
        }else{
            return new ResponseEntity("Não existe um cadastro com esse id",HttpStatus.BAD_REQUEST);
        }


    }
}
