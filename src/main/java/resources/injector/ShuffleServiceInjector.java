package resources.injector;

import resources.objects.ExpandedDeck;

public interface ShuffleServiceInjector {
    ExpandedDeck getDeck(String name);
}
