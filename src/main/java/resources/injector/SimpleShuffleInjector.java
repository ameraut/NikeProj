package resources.injector;

import resources.objects.NikeDeck;
import resources.service.SimpleShuffleService;

import java.util.ArrayList;

/**
 * Created by Jon on 2/11/2017.
 */
public class SimpleShuffleInjector implements ShuffleServiceInjector {
    @Override
    public NikeDeck getDeck(String name){
        return new NikeDeck(name, new SimpleShuffleService());
    }
}
