package org.capybara.dictionaryofcolour;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestControllerTest {

    @Test
     void getRandomHex() throws IOException {
        Pattern p = Pattern.compile("^#[0-9a-f]{6}$");
        RestController rc = new RestController(new ColourService());
        assertEquals(3, rc.getRandomHex(3).length);
        assertEquals(10, rc.getRandomHex(10).length);
        assertEquals(2, rc.getRandomHex(2).length);
        assertEquals(0, rc.getRandomHex(0).length);
        assertEquals(15, rc.getRandomHex(15).length);

        var hex = rc.getRandomHex(1)[0];
        Matcher m = p.matcher(hex);
        assertTrue(m.matches());
    }

    @Test
    void getRandomRgb() throws IOException {
        Pattern p = Pattern.compile("^\\d{1,3},\\d{1,3},\\d{1,3}$");
        RestController rc = new RestController(new ColourService());
        var rgb = rc.getRandomRgb(1)[0];
        Matcher m = p.matcher(rgb);
        assertTrue(m.matches());

    }
}