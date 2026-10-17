
package decoder.stage;

import java.util.HashMap;
import java.util.Map;

public class MorseCode implements DecoderStage {

    private static final Map<String, Character> MORSE_MAP = createMorseMap();//create morsemap

    private static Map<String, Character> createMorseMap() {
        Map<String, Character> map = new HashMap<>();  //creat opject map 
            //morse with key and value Thanks chat gpt
        map.put(".-", 'a');
        map.put("-...", 'b');
        map.put("-.-.", 'c');
        map.put("-..", 'd');
        map.put(".", 'e');
        map.put("..-.", 'f');
        map.put("--.", 'g');
        map.put("....", 'h');
        map.put("..", 'i');
        map.put(".---", 'j');
        map.put("-.-", 'k');
        map.put(".-..", 'l');
        map.put("--", 'm');
        map.put("-.", 'n');
        map.put("---", 'o');
        map.put(".--.", 'p');
        map.put("--.-", 'q');
        map.put(".-.", 'r');
        map.put("...", 's');
        map.put("-", 't');
        map.put("..-", 'u');
        map.put("...-", 'v');
        map.put(".--", 'w');
        map.put("-..-", 'x');
        map.put("-.--", 'y');
        map.put("--..", 'z');

        map.put(".----", '1');
        map.put("..---", '2');
        map.put("...--", '3');
        map.put("....-", '4');
        map.put(".....", '5');
        map.put("-....", '6');
        map.put("--...", '7');
        map.put("---..", '8');
        map.put("----.", '9');
        map.put("-----", '0');

        return Map.copyOf(map);  //return copy of map but can fix it
    }

    @Override
    public String getName() {
        return "Morse Code"; //same at binary
    }

    @Override
    public String process(String input) {
        if (input == null || input.isBlank()) { //alert blank input
            throw new IllegalArgumentException(
                    "Please enter Morse code."
            );
        }

        String[] words = input.trim().split("\\s*/\\s*"); //split morse code
        StringBuilder output = new StringBuilder();

        for (int w = 0; w < words.length; w++) {
            if (words[w].isBlank()) {
                throw new IllegalArgumentException(
                        "Empty word found in Morse code."
                );
            }

            if (w > 0) {
                output.append(" ");
            }

            String[] symbols = words[w].trim().split("\\s+");

            for (String symbol : symbols) {
                Character letter = MORSE_MAP.get(symbol);

                if (letter == null) {
                    throw new IllegalArgumentException(
                            "Invalid Morse code: " + symbol
                    );
                }

                output.append(letter);
            }
        }

        return output.toString();
    }
}