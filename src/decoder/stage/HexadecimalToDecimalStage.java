/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoder.stage;

/**
 *
 * @author Sirichai Bhunshoo
 */
public class HexadecimalToDecimalStage implements DecoderStage {

    @Override
    public String getName() {
        return "Hexadecimal to Decimal"; // same binary
    }

    @Override
    public String process(String input) {
        long number = Long.parseLong(input, 16); //same at binary but convert to long and input with Hex
        return String.valueOf(number);
    }
}
