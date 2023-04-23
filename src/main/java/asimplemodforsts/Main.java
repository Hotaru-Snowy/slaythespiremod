package asimplemodforsts;

import asimplemodforsts.relics.HolyGrail;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.ISubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class Main implements EditRelicsSubscriber , EditStringsSubscriber, EditCardsSubscriber {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public Main(){
        BaseMod.subscribe((ISubscriber)this);
    }
    public static void initialize(){
        new Main();
    }
    @Override
    public void receiveEditCards() {}
    @Override
    public void receiveEditRelics(){
        logger.log(Level.INFO,"AOIHFSUIDGHJKSADGJKHSADBGKHSDAHB REGISTER RELIC");
        BaseMod.addRelic(new HolyGrail(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "lang/relics/HolyGrail_zh.json");
    }
}
