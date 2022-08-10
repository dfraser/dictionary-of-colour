# A Webservice of Color Combinations

Web service that returns some hex colours based on *Wada Sanzo | A Dictionary of Color Combinations*.
I needed some nice colours for some RGB light bulbs.  A co-worker suggested this dictionary.


Map http port 8080.

Call `/randomRgb/{number}` or `/randomHex/{number}` to get yourself that number of colours based on one of the palettes in A Dictionary of Colour Combinations.

Using colors.json database from: [mattdesl / dictionary-of-colour-combinations](https://github.com/mattdesl/dictionary-of-colour-combinations)

Github: https://github.com/dfraser/dictionary-of-colour

Docker: https://hub.docker.com/r/dfraser2/dictionary-of-colour

I used this in configuration.yaml in HomeAssistant to add a sensor:

```yaml
sensor:
  - platform: rest
    name: dictionary-of-colours
    resource: "http://localhost:8080/randomRgb/3"
```

Then I used this script in HomeAssistant to actually change the lights:

```yaml
alias: Globe Dictionary Colour
sequence:
  - service: homeassistant.update_entity
    target:
      entity_id: sensor.dictionary_of_colours
    data: {}
  - variables:
      json_string: "{{ states('sensor.dictionary_of_colours')|from_json }}"
  - service: light.turn_on
    target:
      entity_id: light.kitchen_inner_globe
    data_template:
      rgb_color: "{{ json_string[0] }}"
      transition: 1
  - service: light.turn_on
    target:
      entity_id: light.kitchen_middle_globe
    data_template:
      rgb_color: "{{ json_string[1] }}"
      transition: 1
  - service: light.turn_on
    target:
      entity_id: light.kitchen_outer_globe
    data_template:
      rgb_color: "{{ json_string[2] }}"
      transition: 1
mode: single
icon: mdi:string-lights
```

