package org.capybara.dictionaryofcolour;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RestControllerTest {

    @Test
     void getRandomHex() throws IOException {
        RestController rc = new RestController(new ColourService());
        assertEquals(3, rc.getRandomHex(3).length);
        assertEquals(10, rc.getRandomHex(10).length);
        assertEquals(2, rc.getRandomHex(2).length);
        assertEquals(0, rc.getRandomHex(0).length);
        assertEquals(15, rc.getRandomHex(15).length);
    }
}