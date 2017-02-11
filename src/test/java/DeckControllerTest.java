import com.sun.jersey.test.framework.JerseyTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;


/**
 * Created by Jon
 */
/*public class DeckControllerTest extends JerseyTest {
    private static final String DECK_NAME = "TheOneTrueDeck";

    @Override
    protected Application configure(){
        return new ResourceConfig(DeckController.class);
    }

    @BeforeClass
    public void setUp(){
        //Setup Jetty Server
    }

    @AfterClass
    public void tearDown(){
        //Terminate Jetty Server
    }
    @Test
    public void getDecksTest() throws Exception {
        System.out.println("I am in the getDecks test.");
        Response output = target("/decks").request().get();
        assertEquals("Should return status 200",200, output.getStatus());
    }

    @Test
    public void getDeckTest() throws Exception {
        System.out.println("I am in the getDeck test.");
        Response output = target("/decks/"+DECK_NAME).request().get();
        assertEquals("Should return status 200",200, output.getStatus());
        assertNotNull("Should return a value", output.getEntity());
    }

    @Test
    public void createDeckTest() throws Exception {
        System.out.println("I am in the createDeck test.");
        NikeDeck newDeck = new NikeDeck();
        Response output = target("/decks").request().post(Entity.entity(newDeck, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 200",200,output.getStatus());
    }

    @Test
    public void updateDeckTest() throws Exception {
        System.out.println("I am in the updateDeck test.");
        NikeDeck updatedDeck = new NikeDeck(DECK_NAME);
        Response output = target("/decks").request().put(Entity.entity());
        assertEquals("Should return status 204",204,output.getStatus());
    }

    @Test
    public void deleteDeckTest() throws Exception {
        System.out.println("I am in the deleteDeck test.");
        Response output = target("/decks/"+DECK_NAME).request().delete();
        assertEquals("Should return status 204",204, output.getStatus());
    }
}*/