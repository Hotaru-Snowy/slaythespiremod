package asimplemodforsts.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RedSkull;

public class HolyGrail extends CustomRelic {
    public static final String ID="holy_grail";

    public HolyGrail() {
        super(ID, ImageMaster.loadImage("images/relics/holy_grail.png"), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.SOLID);
    }
    @Override
    public void atTurnStart(){
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new GainEnergyAction(3));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy() {
        return new HolyGrail();
    }
}