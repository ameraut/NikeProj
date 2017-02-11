package resources.injector;

import resources.objects.NikeDeck;
import resources.service.ComplexShuffleService;

import java.util.ArrayList;

/**
 * Created by Jon on 2/11/2017.
 */
public class ComplexShuffleInjector implements ShuffleServiceInjector {
    @Override
    public NikeDeck getDeck(String name, ArrayList cards) {
        return new NikeDeck(name,cards, new ComplexShuffleService());
    }
}
