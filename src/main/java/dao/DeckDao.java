package dao;

//import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import resources.Deck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon
 */
public class DeckDao {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String path = new File("src/main/java/resources/DeckDirectory").getAbsolutePath();

    public List<Deck> getAllDecks(){
        String path = new File("src/main/java/resources/DeckDirectory/").getAbsolutePath();
        File deckDir = new File(path);

        //Convert File command to get all Files into a List that is used by the rest of the program.
        File[] directoryListing = deckDir.listFiles();
        List<Deck> deckList = new ArrayList<>();

        if(directoryListing!=null) {
            for (File child : directoryListing) {
                try {
                    File childFile = new File(child.getAbsolutePath());
                    Deck deck = mapper.readValue(childFile, Deck.class);
                    deckList.add(deck);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return deckList;
    }

    public Deck getDeck(String name){
        //Get all of the Decks
        List<Deck> decks = getAllDecks();
        //Look through all of the Decks
        for(Deck deck:decks){
            if(deck.getName().equalsIgnoreCase(name)){
                return deck;
            }
        }
        return null;
    }

    //Returns 1 if successful, and returns 0 if not. Used for testing purposes.
    //Should add an event that will hold the process until it is done writing the file
    public int addDeck(String name){
        //Get all Files of Decks
        List<Deck> deckList = getAllDecks();
        boolean deckExists = false;
        //Look through all of the Decks
        for(Deck deck:deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                deckExists = true;
                break;
            }
        }

        if(!deckExists){
            Deck deck = new Deck();
            deck.setName(name);
            //Add File
            deck.writeDeck();
            return 1;
        }
        return 0;
    }

    public void updateDeck(String name, int shuffle){
        //Get all Files of Decks
        List<Deck> deckList = getAllDecks();
        //Look through all of the Decks
        for(Deck deck: deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                if(shuffle==0){
                    deck.simpleShuffle();
                    System.out.println(deck.getCards().toString());
                }else {
                    deck.complexShuffle();
                    System.out.println(deck.getCards().toString());
                }
                //Update File
                deck.writeDeck();
            }
        }
    }

    //Returns 1 if successful, and returns 0 if not. Used for testing purposes.
    public int deleteDeck(String name){
        //Get all Files of Decks
        List<Deck> deckList = getAllDecks();
        //Look through all of the Decks
        for(Deck deck:deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                try{
                    //Delete deck that is found to exist
                    File file = new File(path+"/"+deck.getName()+".json");
                    if(file.delete()){
                        return 1;
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }


}
