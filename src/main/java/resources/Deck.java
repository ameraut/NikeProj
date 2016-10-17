package resources;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Jon Moore
 */
public class Deck {
    private final String name;
    private List<String> cards;

    //Believe the broken portion of code is due to the ObjectMapper getting confused on which of the constructors to use.
    public Deck(){this(null);}
    public Deck(String name){
        this.name = name;
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
    }
    public Deck(String name, List<String> cards){
        this.name = name;
        this.cards = cards;
    }

    public String getName(){return name;}
    public List<String> getCards(){return cards;}
    private void setCards(List<String> cards){this.cards = cards;}


    public void writeDeck() {
        ObjectMapper mapper = new ObjectMapper();
        //Creating directory if the directory does not exist on the machine
        String path = new File("src/main/java/resources/DeckDirectory/").getAbsolutePath();
        File deckDir = new File(path);

        if(!deckDir.exists()){
            try{
                deckDir.mkdir();
            }catch(Exception ex){
                System.out.println("Directory was not created due to following exception: " + ex);
            }
        }

        //Creating File that will be added into the DeckDirectory
        File deckFile = new File(path + this.getName());

        try {
            //Populating the file with a name value along with default card values as a json object
            mapper.writeValue(deckFile, this);
        } catch (Exception ex) {
            System.out.println("Failure in writing the card due to the following exception: " + ex);

        }
    }


    public void simpleShuffle(){
        for(String card:cards){
            int index = cards.indexOf(card);
            Random rand = new Random();
            int randomIndex = rand.nextInt(cards.size());

            while(randomIndex==index){
                randomIndex = rand.nextInt(cards.size());
            }
            String stub = cards.get(randomIndex);
            cards.set(randomIndex, card);
            cards.set(index, stub);
        }
    }
    public void complexShuffle(){
        List leftCards =  cards.subList(0,cards.size()/2);
        List rightCards = cards.subList((cards.size()/2)+1, cards.size()-1);
        //Fun Fact
        //In 1992, Bayer and Diaconis showed that after seven random riffle shuffles of a deck of 52 cards, every
        //configuration is nearly equally likely. Shuffling more than this does not significantly increase the
        //"randomness"; shuffle less than this and the deck is "far" from random.
        for(int i =0;i<7;i++) {
            this.cards = merge(leftCards, rightCards);
        }
    }

    private List<String> merge(List left, List right){
        List cards = new ArrayList();
        //Using Iterators to safely remove objects from
        Iterator leftIterator = left.iterator();
        Iterator rightIterator = right.iterator();

        //Variables to determine which iterator to take from to "interleave" the cards
        int leftSize = left.size();
        int rightSize = right.size();

        //interleave cards and return merged List
        while(leftIterator.hasNext()||rightIterator.hasNext()){
            if(leftSize>rightSize){
                cards.add(leftIterator.next());
                leftIterator.remove();
                leftSize--;
            }else{
                cards.add(rightIterator.next());
                rightIterator.remove();
                rightSize--;
            }
        }
        return cards;
    }
}
