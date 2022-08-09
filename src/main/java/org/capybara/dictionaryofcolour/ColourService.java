package org.capybara.dictionaryofcolour;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.capybara.dictionaryofcolour.model.Colour;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ColourService {

    private final List<Colour> colourList;
    private final List<Integer> combinationList;


    private final long totalCombinations;

    private final Random random = new Random();

    public ColourService() throws IOException {
        colourList = readJson();
        combinationList = getCombinationList();
        totalCombinations = combinationList.size();
    }

    public Optional<List<Colour>> getCombination(int combination) {
        if (combinationList.contains(combination)) {
            return Optional.of(colourList.stream().filter(c -> c.combinations().contains(combination)).collect(Collectors.toList()));
        } else {
            return Optional.empty();
        }
    }

     List<Colour> readJson() throws IOException {
        var colourJsonStream = ColourService.class.getResourceAsStream("/colours.json");
        Objects.requireNonNull(colourJsonStream);
        var mapper = new ObjectMapper();
        var colourArray = mapper.readValue(colourJsonStream, Colour[].class);
        return Arrays.asList(colourArray);
    }

    public int getRandomCombination() {
        return combinationList.get(random.nextInt(combinationList.size()));
    }

    public static List<String> extractHex(List<Colour> colourList) {
        return colourList.stream().map(c -> c.hex()).collect(Collectors.toList());
    }

    List<Integer> getCombinationList() {
       return colourList.stream().flatMap(c -> c.combinations().stream()).distinct().collect(Collectors.toList());
    }
    public long getTotalCombinations() {
        return totalCombinations;
    }

}
