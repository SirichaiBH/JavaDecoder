/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoder.stage;

/**
 *
 * @author Sirichai Bhunshoo
 */
public class BinaryToDecimalStage implements DecoderStage{

    @Override
    public String getName() {
        return "Binary to Decimal";
    }

    @Override
    public String process(String input) {
        int number = Integer.parseInt(input, 2);
        return String.valueOf(number);
    }
    
}
