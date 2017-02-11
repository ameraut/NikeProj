package resources.injector;

import resources.objects.NikeDeck;

import java.util.ArrayList;

/**
 * Created by Jon on 2/11/2017.
 */
public interface ShuffleServiceInjector {
    NikeDeck getDeck(String name, ArrayList cards);
}
