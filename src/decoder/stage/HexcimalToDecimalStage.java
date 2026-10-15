/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoder.stage;

/**
 *
 * @author Sirichai Bhunshoo
 */
public class HexcimalToDecimalStage implements DecoderStage {

    @Override
    public String getName() {
        return "Hexcimal to Decimal";
    }

    @Override
    public String process(String input) {
        long number = Long.parseLong(input, 16);
        return String.valueOf(number);
    }
}
