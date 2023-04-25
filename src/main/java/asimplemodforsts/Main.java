package asimplemodforsts;

import asimplemodforsts.cards.LaserCannon;
import asimplemodforsts.relics.HolyGrail;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//使用注解注册这个类
@SpireInitializer
public class Main implements EditRelicsSubscriber , EditStringsSubscriber, EditCardsSubscriber {
    //消息处理器
    private static final Logger logger = LogManager.getLogger(Main.class);

    //构造方法，初始化构造器
    public Main(){
        BaseMod.subscribe(this);
    }

    //初始化，注册这个类时调用的方法
    public static void initialize(){
        new Main();
    }

    //注册卡牌
    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new LaserCannon());
    }

    //注册遗物
    @Override
    public void receiveEditRelics(){
        logger.log(Level.INFO,"AOIHFSUIDGHJKSADGJKHSADBGKHSDAHB REGISTER RELIC");
        BaseMod.addRelic(new HolyGrail(), RelicType.SHARED);
    }

    //注册语言文件
    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, ResourceLib.LANGZHSPATH+"Relics.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, ResourceLib.LANGZHSPATH+"Cards.json");
    }
}
