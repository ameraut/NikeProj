package resources.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.Random;


@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComplexShuffleService.class, name = "ComplexShuffleService"),
})
public class ComplexShuffleService implements ShuffleService {
    public ArrayList shuffle(ArrayList cards){
        ArrayList tempCards = new ArrayList();
        for (int i = 0; i < 7; i++) {
            tempCards = interleave(cards);
        }
        return tempCards;
    }

    private ArrayList interleave(ArrayList cards) {

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
