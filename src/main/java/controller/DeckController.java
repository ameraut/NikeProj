package controller;

import dao.DeckDao;
import resources.NikeDeck;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


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
    public Response createDeck(@QueryParam("deckName") String name){
        try {
            deckDAO.addDeck(name);
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
            @QueryParam("deckName") String name,
            @QueryParam("shuffle") int shuffle){
        try{
            deckDAO.updateDeck(name,shuffle);
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
