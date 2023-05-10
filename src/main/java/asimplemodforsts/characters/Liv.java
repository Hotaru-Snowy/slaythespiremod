package asimplemodforsts.characters;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.cards.common.LaserCannon;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.pathes.LivmodClassEnum;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

public class Liv extends CustomPlayer {
    public static final String name ="Liv";
    public static final int ENERGY_PER_TURN = 3;
    //以下图片依次作用为[篝火休息处的角色背影2，篝火休息处的角色背影1，角色死亡后倒下的图片，角色平常站立时的图片]
    private static final String SELES_SHOULDER_2 = ResourceLib.CHARAIMGPATH+name+"/liv_shoulder2.png";
    private static final String SELES_SHOULDER_1 = ResourceLib.CHARAIMGPATH+name+"/liv_shoulder1.png";
    private static final String SELES_CORPSE = ResourceLib.CHARAIMGPATH+name+"/liv_fallen.png";
    private static final String SELES_STAND = ResourceLib.CHARAIMGPATH+name+"/liv_stand.png";
    //各种素材，不是很懂
    private static final String[] ORB_TEXTURES = new String[] {
            ResourceLib.livImagePath("1"),
            ResourceLib.livImagePath("2"),
            ResourceLib.livImagePath("3"),
            ResourceLib.livImagePath("4"),
            ResourceLib.livImagePath("5"),
            ResourceLib.livImagePath("6"),
            ResourceLib.livImagePath("2"),
            ResourceLib.livImagePath("3"),
            ResourceLib.livImagePath("4"),
            ResourceLib.livImagePath("5"),
            ResourceLib.livImagePath("6"), };
    private static final String ORB_VFX = ResourceLib.livImagePath("vfx");
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };

    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int HAND_SIZE = 0;
    private static final int STARTING_GOLD = 99;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    public static final Color PINK = CardHelper.getColor(255, 192, 203);
    //构造方法
    public Liv() {
        super(name, LivmodClassEnum.Liv_CLASS, ORB_TEXTURES, ORB_VFX,LAYER_SPEED, null, null);
        this.dialogX = this.drawX + 0.0F * Settings.scale;
        this.dialogY = this.drawY + 220.0F * Settings.scale;
        initializeClass(SELES_STAND, SELES_SHOULDER_2, SELES_SHOULDER_1, SELES_CORPSE,
                getLoadout(),
                0.0F, 5.0F, 240.0F, 300.0F,
                new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        //初始卡组，暂时只有这玩意
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("strike_pink");
        retVal.add("strike_pink");
        retVal.add("strike_pink");
        retVal.add("dodge_pink");
        retVal.add("defend_pink");
        retVal.add("defend_pink");
        retVal.add("defend_pink");
        retVal.add("laser_cannon");
        retVal.add("laser_cannon");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物，暂时只有这个超模的遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("holy_grail");
        UnlockTracker.markRelicAsSeen("holy_grail");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        //选人物界面描述
        String title="丽芙·极昼·绮愿稚梦";
        String flavor="磨难，在烈焰中焚毁。——希望，在灰烬中重生。";

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,HAND_SIZE , STARTING_GOLD, ASCENSION_MAX_HP_LOSS, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        //进游戏后左上角人物名
        return "丽芙·极昼·绮愿稚梦";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        //选择卡牌颜色
        return AbstractCardEnum.Liv_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        //卡牌颜色
        return PINK;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        //添加除了攻防以外的初始卡（每日挑战混合那几张）
        return new LaserCannon();
    }

    @Override
    public Color getCardTrailColor() {
        return PINK;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        //能量字体颜色，可以自己写（但是没写
        return FontHelper.energyNumFontPurple;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "丽芙·极昼·绮愿稚梦";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Liv();
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public Color getSlashAttackColor() {
        return PINK;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return null;
    }
}
