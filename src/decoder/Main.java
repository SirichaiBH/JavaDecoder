/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decoder;

/**
 *
 * @author Sirichai Bhunshoo
 */

import decoder.gui.DecoderFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import decoder.stage.*;

public class Main {

    public static void main(String[] args) {
        
        //Some gui Code
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            System.err.println(
                "Could not set system look and feel."
            );
        }

        SwingUtilities.invokeLater(() -> {
            DecoderFrame frame = new DecoderFrame();
            frame.setVisible(true);
        });
        
        
        BinaryToDecimalStage bitode = new BinaryToDecimalStage();
        
        
    }
}
