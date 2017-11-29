package resources.injector;

import resources.objects.ExpandedDeck;
import resources.service.SimpleShuffleService;

public class SimpleShuffleInjector implements ShuffleServiceInjector {
    @Override
    public ExpandedDeck getDeck(String name){
        return new ExpandedDeck(name, new SimpleShuffleService());
    }
}
