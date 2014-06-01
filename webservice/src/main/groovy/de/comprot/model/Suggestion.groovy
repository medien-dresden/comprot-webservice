package de.comprot.model

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class Suggestion {

    String label;
    int hits;

}
