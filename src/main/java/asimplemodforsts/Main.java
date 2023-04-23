package asimplemodforsts;

import asimplemodforsts.relics.HolyGrail;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class Main {
    public Main(){

    }
    public static void initialize(){
        new Main();
    }
    public void receiveEditRelics(){
        BaseMod.addRelic(new HolyGrail(), RelicType.SHARED);
    }
}
