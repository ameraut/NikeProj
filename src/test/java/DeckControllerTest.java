import controller.DeckController;
//import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.*;
import org.junit.Test;
import resources.objects.NikeDeck;
import resources.service.SimpleShuffleService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jon
 */
public class DeckControllerTest extends JerseyTest {
    private static final NikeDeck DECK = new NikeDeck("OneTrueDeck", new SimpleShuffleService());

    /*@Override
    protected Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(DeckController.class);
    }*/

    @Test
    public void getDecksTest() throws Exception {
        Response output = target("/decks").request().get();
        assertEquals("Should return status 200",200, output.getStatus());
    }

    @Test
    public void getDeckTest() throws Exception {
        Response output = target("/decks/"+DECK.getName()).request().get();
        assertEquals("Should return status 200",200, output.getStatus());
        assertNotNull("Should return a value", output.getEntity());
    }

    @Test
    public void createDeckTest() throws Exception {
        Response output = target("/decks").request().post(Entity.entity(DECK.getName(), MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204",204,output.getStatus());
    }

    @Test
    public void updateDeckTest() throws Exception {
        Response output = target("/decks").request().put(Entity.entity(DECK.getName(), MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204",204,output.getStatus());
    }

    @Test
    public void deleteDeckTest() throws Exception {
        Response output = target("/decks/"+DECK.getName()).request().delete();
        assertEquals("Should return status 204",204, output.getStatus());
    }
}