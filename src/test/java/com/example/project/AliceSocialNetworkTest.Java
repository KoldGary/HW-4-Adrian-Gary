package AliceNetworkTest; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions;

class AliceNetworkTest
    void userCanPostMessagesToTheirTimeline() {

        AliceNetworkTest = new SocialNetwork();
        User alice = network.registerUser("alice")
        alice.postMessage("Can This Thing Work???");
        assertEquals(1, alice.getTimeline().size());
        assertEquals("Hello world!", alice.getTimeline().get(0));
    }
}