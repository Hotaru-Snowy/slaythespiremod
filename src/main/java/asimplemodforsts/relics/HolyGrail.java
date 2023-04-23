package asimplemodforsts.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.RedSkull;

public class HolyGrail extends CustomRelic {
    public static final String ID="holy grail";

    public HolyGrail() {
        super(ID, "holy_grail.png", AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.SOLID);
    }

    public void atTurnStart(){
        flash();
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new GainEnergyAction(3));
    }

    public AbstractRelic makeCopy() {
        return new HolyGrail();
    }
}