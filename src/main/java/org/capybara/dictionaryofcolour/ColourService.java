package org.capybara.dictionaryofcolour;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.capybara.dictionaryofcolour.model.Colour;
import org.springframework.lang.NonNull;
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

    public static List<String> extractHex(@NonNull List<Colour> colourList) {
        Objects.requireNonNull(colourList);
        return colourList.stream().map(Colour::hex).collect(Collectors.toList());
    }

    /**
     * Forces a colour combination to have a certain number of colours. If it doesn't have enough,
     * duplicate colours until we reach the right size. If it doesn't have enough, truncate it.
     * @param combination a list of colours from which to generate the list
     * @param number the number of colours in the result
     * @return a list containing the specified number of colours.
     */
    public static List<Colour> getSpecificNumberOfColours(@NonNull List<Colour> combination, int number) {
        Objects.requireNonNull(combination);
        assert(combination.size() > 0);
        if (combination.size() >= number) {
            return combination.subList(0, number);
        } else {
            var expandedList = new ArrayList<Colour>();
            for (int i = 0; i < number; i++) {
                expandedList.add(combination.get(i % combination.size()));
            }
            return expandedList;
        }
    }

    List<Integer> getCombinationList() {
       return colourList.stream().flatMap(c -> c.combinations().stream()).distinct().collect(Collectors.toList());
    }
    public long getTotalCombinations() {
        return totalCombinations;
    }

}
