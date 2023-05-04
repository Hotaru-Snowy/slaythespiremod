package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DodgePower extends AbstractPower {
    public static final String POWER_ID = "DodgePower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings("DodgePower");
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;

    public DodgePower(AbstractCreature owner, int dodgeAmt){
        this.name=NAME;
        this.ID=POWER_ID;
        this.owner = owner;
        this.amount = dodgeAmt;
        this.updateDescription();
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount = stackAmount;
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
        return 0;
    }
    public void atStartOfTurn() {
        addToBot((AbstractGameAction)new ChangeStanceAction("Wrath"));
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

    }
}
