package resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
//import java.util.List;
import java.util.Random;

/**
 * Created by Jon Moore
 */
public class Deck {
    private String name;
    private ArrayList cards;

    public Deck() {
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


    public void writeDeck() {
        ObjectMapper mapper = new ObjectMapper();
        String path = new File("src/main/java/resources/DeckDirectory").getAbsolutePath();

        //Creating File that will be added into the DeckDirectory
        File deckFile = new File(path +"/"+ this.getName() + ".json");
        ArrayList cards = new ArrayList();
        //region Add Default cards
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
        this.setCards(cards);
        //endregion

        try {
            //Writing file
            mapper.writeValue(deckFile, this);
        } catch (Exception ex) {
            System.out.println("Failure in writing the card due to the following exception: " + ex);

        }
    }

    public void simpleShuffle() {
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
    }

    public void complexShuffle() {
        /*List leftCards = cards.subList(1,2);
        List rightCards = cards.subList((cards.size() / 2) + 1, cards.size() - 1);*/
        //Fun Fact
        //In 1992, Bayer and Diaconis showed that after seven random riffle shuffles of a deck of 52 cards, every
        //configuration is nearly equally likely. Shuffling more than this does not significantly increase the
        //"randomness"; shuffle less than this and the deck is "far" from random.
        ArrayList tempCards = new ArrayList();
        for (int i = 0; i < 7; i++) {
           tempCards = interleave();
        }
        this.setCards(tempCards);
    }

    private ArrayList interleave() {
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
