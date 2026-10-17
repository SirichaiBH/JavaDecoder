/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package decoder.core;

import decoder.stage.DecoderStage;
import decoder.stage.BinaryToDecimalStage;
import decoder.stage.HexadecimalToDecimalStage;
import decoder.stage.MorseCode;

import java.util.LinkedHashMap;
import java.util.Map;

public class StageManager {

    private final Map<String, DecoderStage> stages = new LinkedHashMap<>();

    public StageManager() {  //add method
        registerStage(new BinaryToDecimalStage());
        registerStage(new HexadecimalToDecimalStage());
        registerStage(new MorseCode());
        
    }

    public void registerStage(DecoderStage stage) {
        stages.put(stage.getName(), stage);
    }

    public DecoderStage getStage(String name) {
        return stages.get(name);
    }

    public String execute(String stageName, String input) {
        DecoderStage stage = getStage(stageName);

        if (stage == null) {
            throw new IllegalArgumentException(
                    "Stage not found: " + stageName
            );
        }

        return stage.process(input);
    }
}
