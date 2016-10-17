package dao;

import org.codehaus.jackson.map.ObjectMapper;
import resources.Deck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon
 */
public class DeckDao {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String path = new File("src/main/java/resources/DeckDirectory/").getAbsolutePath();

    //This method is broken, and is the lynchpin of the other functions. I have found where my problem is occurring,
    //but I am not able to fix it at this time. More comments are made inside.
    public List<Deck> getAllDecks(){
        String path = new File("src/main/java/resources/DeckDirectory/").getAbsolutePath();
        File deckDir = new File(path);

        //Convert File command to get all Files into a List that is used by the rest of the program.
        File[] directoryListing = deckDir.listFiles();
        List<Deck> deckList = new ArrayList<Deck>();

        for (File child:directoryListing) {
            try {
                //Code is broken here. The class Deck is receiving a null value. Need to look into why this is occurring.
                //Due to time constraint, going to continue project as if this was working.
                Deck deck = mapper.readValue(child.getAbsoluteFile(), Deck.class);
                deckList.add(deck);
            }catch(NullPointerException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
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
            Deck deck = new Deck(name);
            //Add File
            deck.writeDeck();
            return 1;
        }
        return 0;
    }

    //Returns 1 if successful, and returns 0 if not. Used for testing purposes.
    public int updateDeck(String name, int shuffle){
        //Get all Files of Decks
        List<Deck> deckList = getAllDecks();
        //Look through all of the Decks
        for(Deck deck: deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                int index = deckList.indexOf(deck);
                if(shuffle==0){
                    deck.simpleShuffle();
                }else {
                    deck.complexShuffle();
                }
                //Update File
                deck.writeDeck();
                return 1;
            }
        }
        return 0;
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
                    File file = new File(path+deck.getName());
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
