package asimplemodforsts.relics;

import asimplemodforsts.ResourceLib;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//遗物类：圣杯
public class HolyGrail extends CustomRelic {
    //注册ID
    public static final String ID="holy_grail";
    private static final Texture img = ImageMaster.loadImage(ResourceLib.RELICIMGPATH +ID+".png");
    //遗物构造方法
    public HolyGrail() {
        super(ID, img, AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.SOLID);
    }

    //回合开始时调用
    @Override
    public void atTurnStart(){
        //动画
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        //加三费用
        addToBot(new GainEnergyAction(3));
    }
    //更新描述
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    //复制方法
    @Override
    public AbstractRelic makeCopy() {
        return new HolyGrail();
    }
}