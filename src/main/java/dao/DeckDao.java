package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import resources.objects.ExpandedDeck;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeckDao {
    private static ObjectMapper mapper = new ObjectMapper();
    private static final String path = new File("src/main/resources/DeckDirectory").getAbsolutePath();

    public List<ExpandedDeck> getAllDecks(){
        File deckDir = new File(path);

        //Convert File command to get all Files into a List that is used by the rest of the program.
        File[] directoryListing = deckDir.listFiles();
        List<ExpandedDeck> deckList = new ArrayList<>();

        if(directoryListing!=null) {
            for (File child : directoryListing) {
                try {
                    File childFile = new File(child.getAbsolutePath());
                    ExpandedDeck deck = mapper.readValue(childFile, ExpandedDeck.class);
                    deckList.add(deck);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return deckList;
    }

    public ExpandedDeck getDeck(String name){
        List<ExpandedDeck> decks = getAllDecks();
        for(ExpandedDeck deck:decks){
            if(deck.getName().equalsIgnoreCase(name)){
                return deck;
            }
        }
        return null;
    }

    public void addDeck(ExpandedDeck deck){
        List<ExpandedDeck> deckList = getAllDecks();
        boolean deckExists = false;

        for(ExpandedDeck x:deckList){
            if(x.getName().equalsIgnoreCase(deck.getName())){
                deckExists = true;
                break;
            }
        }
        if(!deckExists){
            writeDeck(deck);
        }
    }

    public void updateDeck(String name){
        List<ExpandedDeck> deckList = getAllDecks();

        for(ExpandedDeck deck: deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                deck.processShuffle();
                writeDeck(deck);
            }
        }
    }

    public void deleteDeck(String name){
        List<ExpandedDeck> deckList = getAllDecks();
        for(ExpandedDeck deck:deckList){
            if(deck.getName().equalsIgnoreCase(name)){
                try{
                    File file = new File(path+"/"+deck.getName()+".json");
                    file.delete();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    //Helper Method
    private void writeDeck(ExpandedDeck deck){
        ObjectMapper mapper = new ObjectMapper();
        File deckFile = new File(path +"/"+ deck.getName() + ".json");

        try {
            mapper.writeValue(deckFile, deck);
        } catch (Exception ex) {
            System.out.println("Failure in writing the card due to the following exception: " + ex);
        }
    }

}
