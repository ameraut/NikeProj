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
    public NikeDeck(String name, ShuffleService shuffleService){
        this.name=name;
        //region Add Default cards
        ArrayList cards = new ArrayList();
        cards.add("2-heart");
        cards.add("3-heart");
        cards.add("4-heart");
        cards.add("5-heart");
        cards.add("6-heart");
        cards.add("7-heart");
        cards.add("8-heart");
        cards.add("9-heart");
        cards.add("10-heart");
        cards.add("J-heart");
        cards.add("Q-heart");
        cards.add("K-heart");
        cards.add("A-heart");
        cards.add("2-diamond");
        cards.add("3-diamond");
        cards.add("4-diamond");
        cards.add("5-diamond");
        cards.add("6-diamond");
        cards.add("7-diamond");
        cards.add("8-diamond");
        cards.add("9-diamond");
        cards.add("10-diamond");
        cards.add("J-diamond");
        cards.add("Q-diamond");
        cards.add("K-diamond");
        cards.add("A-diamond");
        cards.add("2-spades");
        cards.add("3-spades");
        cards.add("4-spades");
        cards.add("5-spades");
        cards.add("6-spades");
        cards.add("7-spades");
        cards.add("8-spades");
        cards.add("9-spades");
        cards.add("10-spades");
        cards.add("J-spades");
        cards.add("Q-spades");
        cards.add("K-spades");
        cards.add("A-spades");
        cards.add("2-clubs");
        cards.add("3-clubs");
        cards.add("4-clubs");
        cards.add("5-clubs");
        cards.add("6-clubs");
        cards.add("7-clubs");
        cards.add("8-clubs");
        cards.add("9-clubs");
        cards.add("10-clubs");
        cards.add("J-clubs");
        cards.add("Q-clubs");
        cards.add("K-clubs");
        cards.add("A-clubs");
        //endregion
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
        this.cards = this.shuffleService.shuffle(cards);
    }
}
