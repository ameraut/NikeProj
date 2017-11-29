package resources.injector;

import resources.objects.ExpandedDeck;
import resources.service.ComplexShuffleService;

public class ComplexShuffleInjector implements ShuffleServiceInjector {
    @Override
    public ExpandedDeck getDeck(String name) {
        return new ExpandedDeck(name, new ComplexShuffleService());
    }
}
