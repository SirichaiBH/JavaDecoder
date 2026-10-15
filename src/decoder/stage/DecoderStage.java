package decoder.stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Sirichai Bhunshoo
 */
public interface DecoderStage {
    String getName();
    String process(String input);
}
