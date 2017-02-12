package controller;

import dao.DeckDao;
import resources.injector.*;
import resources.objects.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/decks")
public class DeckController {
    private DeckDao deckDAO = new DeckDao();

    @GET
    public Response getDecks(){
        List<NikeDeck> decks = deckDAO.getAllDecks();
        String deckNames = "";
        for (NikeDeck deck: decks) {
            if(deckNames.equals("")){
                deckNames+=deck.getName();
            }else{
                deckNames+=", "+deck.getName();
            }
        }
        return Response.status(200).entity("getDecks is called, names of all decks are: " + deckNames).build();
    }

    @GET
    @Path("/{deckName}")
    @Produces(MediaType.APPLICATION_JSON)
    public NikeDeck getDeck(@PathParam("deckName") String deckName){
        NikeDeck deck = deckDAO.getDeck(deckName);
        return deck;
    }

    @POST
    @Path("/deck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeck(@QueryParam("deckName") String name,@QueryParam("shuffleType") String shuffleService){
        ShuffleServiceInjector injector;
        NikeDeck deck;
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
        try {
            if(shuffleService.equalsIgnoreCase("complex")){
                injector = new ComplexShuffleInjector();
                deck = injector.getDeck(name, cards);
            }else{
                injector = new SimpleShuffleInjector();
                deck = injector.getDeck(name, cards);
            }
            deckDAO.addDeck(deck);

            return Response.status(204).entity("createDeck is called, name of the successfully created deck is: "
                    + name).build();
        }catch(Exception ex){
            ex.printStackTrace();
            return Response.status(500).entity("createDeck is called, but did not make the deck successfully. " +
                    "Please try again.").build();
        }

    }

    @PUT
    @Path("/deck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeck(
            @QueryParam("deckName") String name){
        try{
            deckDAO.updateDeck(name);
            return Response.status(204).entity("updateDeck is called.").build();
        }catch(Exception ex){
            ex.printStackTrace();
            return Response.status(500).entity("updateDeck is called, but did not update").build();
        }

    }

    @DELETE
    @Path("/{deckName}")
    public Response deleteDeck(@PathParam("deckName") String deckName){
        try{
            deckDAO.deleteDeck(deckName);
            return Response.status(204).entity("deleteDeck is called.").build();
        }catch(Exception ex){
            ex.printStackTrace();
            return Response.status(500).entity("deleteDeck is called, but did not delete.").build();
        }

    }
}
