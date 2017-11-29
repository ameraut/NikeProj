package controller;

import dao.DeckDao;
import resources.injector.*;
import resources.objects.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/decks")
public class DeckController {
    private DeckDao deckDAO = new DeckDao();

    @GET
    public Response getDecks(){
        List<ExpandedDeck> decks = deckDAO.getAllDecks();
        String deckNames = "";
        for (ExpandedDeck deck: decks) {
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
    public Response getDeck(@PathParam("deckName") String deckName){
        ExpandedDeck deck = deckDAO.getDeck(deckName);
        if(deck==null){
            return Response.status(400).entity("getDeck is called, but the deck you requested does not exist.").build();
        }
        return Response.status(204).entity(deck).build();
    }

    @POST
    @Path("/deck")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeck(@QueryParam("deckName") String name,@QueryParam("shuffleType") String shuffleService){
        ShuffleServiceInjector injector;
        ExpandedDeck deck;
        if(name.isEmpty()){
            return Response.status(400).entity("createDeck is called, but an improper deck name has been " +
                    "given").build();
        }
        try {
            if(shuffleService.equalsIgnoreCase("complex")){
                injector = new ComplexShuffleInjector();
                deck = injector.getDeck(name);
            }else if(shuffleService.equalsIgnoreCase("simple")){
                injector = new SimpleShuffleInjector();
                deck = injector.getDeck(name);
            }else{
                return Response.status(400).entity("createDeck is called, but an unsupported shuffle was requested. " +
                        "Please try again with a supported shuffle.").build();
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
