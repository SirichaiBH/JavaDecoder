
package decoder.stage;
public class MorseCode implements DecoderStage {

    @Override
    public String getName() {
        return "Morse Code";
    }

    @Override
    public String process(String input) {

        char[] letter = {
            'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '0'
        };

        String[] code = {
            ".-", "-...", "-.-.", "-..", ".",
            "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--",
            "--..", ".----", "..---", "...--", "....-",
            ".....", "-....", "--...", "---..", "----.", "-----"
        };

        StringBuilder output = new StringBuilder();

        // / ใช้คั่นระหว่างคำ
        String[] words = input.trim().split("\\s*/\\s*");

        for (int w = 0; w < words.length; w++) {

            if (w > 0) {
                output.append(" ");
            }

            String[] symbols = words[w].trim().split("\\s+");

            for (String symbol : symbols) {

                for (int j = 0; j < code.length; j++) {

                    if (code[j].equals(symbol)) {
                        output.append(letter[j]);
                        break;
                    }
                }
            }
        }

        return output.toString();
    }
}
