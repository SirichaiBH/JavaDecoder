/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Sirichai Bhunshoo
 */
package decoder;

import decoder.core.StageManager;

public class Test {

    public static void main(String[] args) {
        StageManager manager = new StageManager();

        String result = manager.execute(
                "Binary to Decimal",
                "1010"
        );

        System.out.println(result);
    }
}