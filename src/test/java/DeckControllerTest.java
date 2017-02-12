import controller.DeckController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.*;
import org.junit.Test;
import resources.objects.NikeDeck;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Jon
 */
public class DeckControllerTest extends JerseyTest {
    private static final String DECK_NAME = "TheOneTrueDeck";

    @Override
    protected Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(DeckController.class);
    }

    @Test
    public void getDecksTest() throws Exception {
        Response output = target("/decks").request().get();
        assertEquals("Should return status 200",200, output.getStatus());
    }

    @Test
    public void getDeckTest() throws Exception {
        Response output = target("/decks/"+DECK_NAME).request().get();
        assertEquals("Should return status 200",200, output.getStatus());
        assertNotNull("Should return a value", output.getEntity());
    }

    @Test
    public void createDeckTest() throws Exception {
        NikeDeck newDeck = new NikeDeck();
        Response output = target("/decks").request().post(Entity.entity(newDeck, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204",204,output.getStatus());
    }

    @Test
    public void updateSimpleDeckTest() throws Exception {
        Response output = target("/decks").request().put(Entity.entity(DECK_NAME, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204",204,output.getStatus());
    }

    @Test
    public void deleteDeckTest() throws Exception {
        Response output = target("/decks/"+DECK_NAME).request().delete();
        assertEquals("Should return status 204",204, output.getStatus());
    }
}