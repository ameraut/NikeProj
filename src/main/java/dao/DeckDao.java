package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import resources.objects.NikeDeck;
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

    public void addDeck(NikeDeck deck){
        //Get all Files of Decks
        List<NikeDeck> deckList = getAllDecks();
        boolean deckExists = false;

        //Look through all of the Decks
        for(NikeDeck x:deckList){
            if(x.getName().equalsIgnoreCase(deck.getName())){
                deckExists = true;
                break;
            }
        }
        //If Deck with assigned deck name doesn't exist, write deck into database
        if(!deckExists){
            writeDeck(deck);
        }
    }

    public void updateDeck(String name){
        //Get all Files of Decks
        List<NikeDeck> deckList = getAllDecks();

        //Look through all of the Decks
        for(NikeDeck deck: deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                //Shuffle Deck
                deck.processShuffle();
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

        try {
            //Writing file
            mapper.writeValue(deckFile, deck);
        } catch (Exception ex) {
            System.out.println("Failure in writing the card due to the following exception: " + ex);
        }
    }

}
