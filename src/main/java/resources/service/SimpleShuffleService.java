package resources.service;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Random;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleShuffleService.class, name = "SimpleShuffleService"),
})
public class SimpleShuffleService implements ShuffleService {
    public ArrayList shuffle(ArrayList cards){
        for (Object card : cards) {
            int index = cards.indexOf(card);
            Random rand = new Random();
            int randomIndex = rand.nextInt(cards.size());

            while (randomIndex == index) {
                randomIndex = rand.nextInt(cards.size());
            }
            String stub = cards.get(randomIndex).toString();
            cards.set(randomIndex, card);
            cards.set(index, stub);
        }
        return cards;
    }
}
