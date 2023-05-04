package asimplemodforsts;

import asimplemodforsts.cards.basic.Defend_Pink;
import asimplemodforsts.cards.basic.Dodge_Pink;
import asimplemodforsts.cards.basic.Strike_Pink;
import asimplemodforsts.cards.common.LaserCannon;
import asimplemodforsts.characters.Liv;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.pathes.LivmodClassEnum;
import asimplemodforsts.relics.HolyGrail;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//使用注解注册这个类
@SpireInitializer
public class Main implements EditRelicsSubscriber , EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber{
    //消息处理器
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static final Color PINK = CardHelper.getColor(255, 192, 203);
    //构造方法，初始化构造器
    public Main(){
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.Liv_COLOR,
                PINK, PINK, PINK, PINK, PINK, PINK, PINK,
                "images/512/bg_attack_default_gray.png",
                "images/512/bg_skill_default_gray.png",
                "images/512/bg_power_default_gray.png",
                "images/512/energy_ball.png",
                "images/1024/bg_attack_default_gray.png",
                "images/1024/bg_skill_default_gray.png",
                "images/1024/bg_power_default_gray.png",
                "images/1024/energy_ball.png",
                "images/512/energy_orb.png");
    }

    //初始化，注册这个类时调用的方法
    public static void initialize(){
        new Main();
    }

    //注册卡牌
    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new LaserCannon());
        BaseMod.addCard(new Strike_Pink());
        BaseMod.addCard(new Defend_Pink());
        BaseMod.addCard(new Dodge_Pink());
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
        BaseMod.loadCustomStringsFile(RelicStrings.class, ResourceLib.langFilePath("Relics"));
        BaseMod.loadCustomStringsFile(CardStrings.class, ResourceLib.langFilePath("Cards"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, ResourceLib.langFilePath("Powers"));
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Liv(), ResourceLib.CHARAIMGPATH+Liv.name+"/liv_button.png", ResourceLib.CHARAIMGPATH+Liv.name+"/liv_bg.png", LivmodClassEnum.Liv_CLASS);
    }
}
