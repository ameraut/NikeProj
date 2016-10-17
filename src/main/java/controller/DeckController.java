package controller;

import dao.DeckDao;
import resources.Deck;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


@Path("/decks")
public class DeckController {
    private DeckDao deckDAO = new DeckDao();
    List<Deck> Decks;

    /*@GET
    public Response getHelloWorld(){

        return Response.status(200).entity("Hello World").build();
    }*/

    @GET
    public Response getDecks(){
        return Response.status(200).entity("getDecks is called, names of all decks are: "
                + deckDAO.getAllDecks().toString()).build();
    }

    @GET
    @Path("/{deckName}")
    public Response getDeck(@PathParam("deckName") String deckName){
        if(deckDAO.getAllDecks().size()==0){
            return Response.status(200).entity("getDeck is called, but there are no decks to get.").build();
        }else if(deckDAO.getDeck(deckName).toString().isEmpty()){
            return Response.status(200).entity("getDeck is called, but the deck you requested is not here.").build();
        }
        return Response.status(200).entity("getDeck is called, name of the deck is: "
                + deckDAO.getDeck(deckName).toString()).build();
    }

    @PUT
    public Response createDeck(@FormParam("deckName") String name)throws IOException{
        int result = deckDAO.addDeck(name);
        if(result==1){
            return Response.status(200).entity("createDeck is called, name of the created deck is: "
                    + name).build();
        }
        return Response.status(500).build();
    }

    @POST
    public Response updateDeck(
            @FormParam("deckName") String name,
            @FormParam("shuffle") int shuffle) throws IOException{
        int result = deckDAO.updateDeck(name,shuffle);
        if(result==1){
            return Response.status(200).entity("updateDeck is called.").build();
        }
        return Response.status(500).build();
    }

    @DELETE
    @Path("/{deckName}")
    public Response deleteDeck(@PathParam("deckName") String deckName){
        int result = deckDAO.deleteDeck(deckName);
        if(result == 1){
            return Response.status(200).entity("deletedDeck is called.").build();
        }
        return Response.status(500).build();
    }
}
