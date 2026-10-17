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
        return "Binary to Decimal"; //use for define state
    }

    @Override
    public String process(String input) {
        int number = Integer.parseInt(input, 2); // input some binary with strign and conmvert to int
        return String.valueOf(number);//return this
    }
    
}
