package org.capybara.dictionaryofcolour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ColourService colourService;

    public RestController(ColourService colourService) {
        this.colourService = colourService;
    }

    @GetMapping("/randomHex/{number}")
    String[] getRandomHex(@PathVariable int number) {
        var combination = colourService.getRandomCombination();
        var colours = colourService.getCombination(combination);
        var hexColours = ColourService.extractHex(colours.get());
        if (hexColours.size() >= number) {
            return hexColours.subList(0, number).toArray(String[]::new);
        } else {
            var expandedList = new ArrayList<String>();
            for (int i = 0; i < number; i++) {
                expandedList.add(hexColours.get(i % hexColours.size()));
            }
            return expandedList.toArray(String[]::new);
        }
    }
}
