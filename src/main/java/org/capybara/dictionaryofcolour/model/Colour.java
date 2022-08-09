package org.capybara.dictionaryofcolour.model;

import java.util.List;

public record Colour (String name,
                      List<Integer> combinations,
                      int swatch,
                      List<Integer> cmyk,
                      List<Integer> lab,
                      List<Integer> rgb,
                      String hex) {}
