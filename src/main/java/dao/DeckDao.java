package dao;

//import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import resources.NikeDeck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon
 */
public class DeckDao {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String path = new File("src/main/resources/DeckDirectory").getAbsolutePath();

    public List<NikeDeck> getAllDecks(){
        File deckDir = new File(path);

        //Convert File command to get all Files into a List that is used by the rest of the program.
        File[] directoryListing = deckDir.listFiles();
        List<NikeDeck> deckList = new ArrayList<>();

        if(directoryListing!=null) {
            for (File child : directoryListing) {
                try {
                    File childFile = new File(child.getAbsolutePath());
                    NikeDeck deck = mapper.readValue(childFile, NikeDeck.class);
                    deckList.add(deck);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return deckList;
    }

    public NikeDeck getDeck(String name){
        //Get all of the Decks
        List<NikeDeck> decks = getAllDecks();
        //Look through all of the Decks
        for(NikeDeck deck:decks){
            if(deck.getName().equalsIgnoreCase(name)){
                return deck;
            }
        }
        return null;
    }

    //Should add an event that will hold the process until it is done writing the file
    public void addDeck(String name){
        //Get all Files of Decks
        List<NikeDeck> deckList = getAllDecks();
        boolean deckExists = false;
        //Look through all of the Decks
        for(NikeDeck deck:deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                deckExists = true;
                break;
            }
        }

        if(!deckExists){
            NikeDeck deck = new NikeDeck();
            deck.setName(name);
            //Add File
            writeDeck(deck);
        }
    }

    public void updateDeck(String name, int shuffle){
        //Get all Files of Decks
        List<NikeDeck> deckList = getAllDecks();
        //Look through all of the Decks
        for(NikeDeck deck: deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                if(shuffle==0){
                    deck.simpleShuffle();
                    System.out.println(deck.getCards().toString());
                }else {
                    deck.complexShuffle();
                    System.out.println(deck.getCards().toString());
                }
                //Update File
                writeDeck(deck);
            }
        }
    }

    public void deleteDeck(String name){
        //Get all Files of Decks
        List<NikeDeck> deckList = getAllDecks();
        //Look through all of the Decks
        for(NikeDeck deck:deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                try{
                    //Delete deck that is found to exist
                    File file = new File(path+"/"+deck.getName()+".json");
                    file.delete();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    //Helper Method
    private void writeDeck(NikeDeck deck){
        ObjectMapper mapper = new ObjectMapper();

        //Creating File that will be added into the DeckDirectory
        File deckFile = new File(path +"/"+ deck.getName() + ".json");
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
        deck.setCards(cards);
        //endregion

        try {
            //Writing file
            mapper.writeValue(deckFile, deck);
        } catch (Exception ex) {
            System.out.println("Failure in writing the card due to the following exception: " + ex);
        }
    }

}
