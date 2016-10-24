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
            deck.writeDeck();
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
                deck.writeDeck();
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


}
