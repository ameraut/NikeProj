package resources.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jon on 10/26/2016.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComplexShuffleService.class, name = "ComplexShuffleService"),
})
public class ComplexShuffleService implements ShuffleService {
    public ArrayList shuffle(ArrayList cards){
        //Fun Fact
        //In 1992, Bayer and Diaconis showed that after seven random riffle shuffles of a deck of 52 cards, every
        //configuration is nearly equally likely. Shuffling more than this does not significantly increase the
        //"randomness"; shuffle less than this and the deck is "far" from random.
        ArrayList tempCards = new ArrayList();
        for (int i = 0; i < 7; i++) {
            tempCards = interleave(cards);
        }
        return tempCards;
    }

    //Helper Shuffle method
    private ArrayList interleave(ArrayList cards) {
        //interleave cards and return merged List
        int displacement = cards.size()/2;
        ArrayList tempCards = cards;
        for(int x=0;x<cards.size()/2;x=x+2){
            Random rand = new Random();
            if(rand.nextInt(2)<1){
                tempCards.set(x,cards.get(x));
                tempCards.set(x++,cards.get(x+displacement));
            }else{
                tempCards.set(x,cards.get(x+displacement));
                tempCards.set(x++,cards.get(x));
            }
        }
        return tempCards;
    }
}
