package org.capybara.dictionaryofcolour;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ColourService colourService;

    public RestController(ColourService colourService) {
        this.colourService = colourService;
    }

    @GetMapping("/randomHex/{number}")
    String[] getRandomHex(@PathVariable int number) {
        var combination = colourService.getCombination(colourService.getRandomCombination()).get();
        var colours = ColourService.getSpecificNumberOfColours(combination, number);
        var hexColours = ColourService.extractHex(colours);
        return hexColours.toArray(String[]::new);
    }

    @GetMapping("/randomRgb/{number}")
    String[] getRandomRgb(@PathVariable int number) {
        var combination = colourService.getCombination(colourService.getRandomCombination()).get();
        var colours = ColourService.getSpecificNumberOfColours(combination, number);
        var rgbList = colours.stream().map(c -> String.format("%s,%s,%s", c.rgb().get(0), c.rgb().get(1), c.rgb().get(2))).toList();
        return rgbList.toArray(String[]::new);
    }

}
