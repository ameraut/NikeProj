package controller;

import dao.DeckDao;
import resources.Deck;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


@Path("/decks")
public class DeckController {
    private DeckDao deckDAO = new DeckDao();

    @GET
    public Response getDecks(){
        List<Deck> decks = deckDAO.getAllDecks();
        String deckNames = "";
        for (Deck deck: decks) {
            if(deckNames.equals("")){
                deckNames+=deck.getName();
            }else{
                deckNames+=", "+deck.getName();
            }
        }

        return Response.status(200).entity("getDecks is called, names of all decks are: " +
                deckNames).build();
    }

    @GET
    @Path("/{deckName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Deck getDeck(@PathParam("deckName") String deckName){
        Deck deck = deckDAO.getDeck(deckName);
        return deck;
    }

    @POST
    @Path("/deck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeck(@QueryParam("deckName") String name)throws IOException{
        int result = deckDAO.addDeck(name);
        if(result==1){
            return Response.status(200).entity("createDeck is called, name of the successfully created deck is: "
                    + name).build();
        }
        return Response.status(500).entity("createDeck is called, but did not make the deck successfully. Please try again?").build();
    }

    @PUT
    @Path("/deck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeck(
            @QueryParam("deckName") String name,
            @QueryParam("shuffle") int shuffle) throws IOException{
        deckDAO.updateDeck(name,shuffle);
        return Response.status(200).entity("updateDeck is called.").build();
    }

    @DELETE
    @Path("/{deckName}")
    public Response deleteDeck(@PathParam("deckName") String deckName){
        int result = deckDAO.deleteDeck(deckName);
        if(result == 1){
            return Response.status(200).entity("deleteDeck is called.").build();
        }
        return Response.status(500).entity("deleteDeck is called, but did not delete.").build();
    }
}
