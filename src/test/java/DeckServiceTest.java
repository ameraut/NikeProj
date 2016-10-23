import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Jon on 10/23/2016.
 */
public class DeckServiceTest /*extends JerseyTest*/{
    public static final String testDeckName = "theOneTrueDeck";

    @Test
    public void getAllDecksTest(){
        /*Response output = target("/decks").request().get();
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("should return a string value with a list",output.getEntity());*/
    }

    @Test
    public void createDeckTest(){
        /*Response output *//*= target("/decks").request().post()*//*;
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return notification", output.getEntity());*/
    }

    @Test
    public void getDeckTest(){
        /*Response output = target("/decks/Test-Deck").request().get();
        assertEquals("Should return status 200",200,output.getStatus());
        assertNotNull("Should return a JSON object",output.getEntity());*/
    }

    //Placeholder test
    @Test
    public void updateDeckTest(){
        /*Response output = target("/decks")
                .request()
                .put();*/
        //assertEquals("Should return status 204", 204, output.getStatus());
    }

    @Test
    public void deleteDeckTest(){
        /*Response output = target("/decks/"+testDeckName).request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());*/
    }
}
