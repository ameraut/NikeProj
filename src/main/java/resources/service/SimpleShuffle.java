package resources.service;

import resources.service.Shuffle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jon on 10/26/2016.
 */
public class SimpleShuffle implements Shuffle {
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
