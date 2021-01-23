package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService{

    @Autowired
    private CardRepository cardRepo;

    //Retorna todos os cartões
    @ResponseStatus(code= HttpStatus.OK)
    public List<Card> getCards(){
        return (List<Card>) cardRepo.findAll();
    }

    //Insere um registro na tabela card do banco de dados
    public Card setCard(Card card){
        return cardRepo.save(card);
    }

    //Atualiza um registro existente
    public Card updateCard(Long id,Card cardPostUpdate){
        List<Card> cards = (List<Card>) cardRepo.findAll();

        for(Card c: cards){
            if(c.getId().equals(id)){
                return cardRepo.save(cardPostUpdate);
            }
        }
        return null;
    }

    //Deleta um cartão por id
    public boolean deleteCard(Long id){
        Optional<Card> card = cardRepo.findById(id);

        if(card.isPresent()) {
            cardRepo.deleteById(id);
            return true;
        }
        return false;
    }

    //Retorna um cartão específico
    public Card getCard(Long id){
        List<Card> cards = (List<Card>) cardRepo.findAll();

        for(Card c: cards){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;


    }

}
