package resources.objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import resources.service.ShuffleService;
import java.util.ArrayList;

/**
 * Created by Jon Moore
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXTERNAL_PROPERTY, property="@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ShuffleService.class, name = "ShuffleService")
})
public class NikeDeck implements Deck {
    private String name;
    private ArrayList cards;
    private ShuffleService shuffleService;

    public NikeDeck() {
    }
    public NikeDeck(String name, ArrayList cards, ShuffleService shuffleService){
        this.name=name;
        this.cards = cards;
        this.shuffleService = shuffleService;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList getCards() {
        return cards;
    }
    public void setCards(ArrayList cards) {
        this.cards = cards;
    }
    public String getShuffleService(){return shuffleService.toString();}
    public void setShuffleService(ShuffleService shuffleService){this.shuffleService = shuffleService;}


    public void processShuffle(){
        this.shuffleService.shuffle(cards);
    }
}
