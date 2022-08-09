package org.capybara.dictionaryofcolour;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ColourServiceTest {

    private static final Logger log = LoggerFactory.getLogger(ColourServiceTest.class);

    @Test
    void readJson() throws IOException {
        var cs = new ColourService();
        var colours = cs.readJson();
        assertEquals(159, colours.size());
    }

    @Test
    void getTotalCombinations() throws IOException {
        var cs = new ColourService();
        assertEquals(348,cs.getTotalCombinations());
    }

    @Test
    void getCombination() throws IOException {
        var cs = new ColourService();
        log.info("combination: "+cs.getCombination(100));
    }

    @Test
    void getRandomCombination() throws IOException {
        var cs = new ColourService();
        var randomCombination = cs.getRandomCombination();
        log.info("Combination: "+randomCombination);
        log.info("Colours: "+cs.getCombination(randomCombination));

    }

    @Test
    void extractHex() throws IOException {
        var cs = new ColourService();
        var randomCombination = cs.getRandomCombination();
        var colours = cs.getCombination(randomCombination);
        assertTrue(colours.isPresent());
        log.info("Hex for {}: "+ColourService.extractHex(colours.get()), randomCombination);
    }
}