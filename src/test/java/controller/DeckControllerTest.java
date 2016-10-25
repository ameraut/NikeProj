package controller;

import org.junit.*;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import static org.junit.Assert.*;

/**
 * Created by Jon
 */
public class DeckControllerTest {
    private static final String BASE_URI = "http://localhost:8080/nikedeck";

    @Before
    public void setUp() throws Exception {
        System.out.println("I am in the setup. My address is: "+BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("I am in the tearDown.");
    }

    @Test
    public void getDecks() throws Exception {
        System.out.println("I am in the getDecks test.");
        assertEquals("1","1");
    }

    @Test
    public void getDeck() throws Exception {
        System.out.println("I am in the getDeck test.");
    }

    @Test
    public void createDeck() throws Exception {
        System.out.println("I am in the createDeck test.");
    }

    @Test
    public void updateDeck() throws Exception {
        System.out.println("I am in the updateDeck test.");
    }

    @Test
    public void deleteDeck() throws Exception {
        System.out.println("I am in the deleteDeck test.");
    }

}