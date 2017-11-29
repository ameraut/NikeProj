package resources.service;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;


@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.EXTERNAL_PROPERTY, property="@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleShuffleService.class, name = "SimpleShuffleService"),
        @JsonSubTypes.Type(value = SimpleShuffleService.class, name = "ComplexShuffleService"),
})
public interface ShuffleService {
    ArrayList shuffle (ArrayList cards);
}
