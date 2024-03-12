package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DodgeSucceedPower extends AbstractPower {
    public static final String POWER_ID = "DodgeSucceedPower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings("DodgeSucceedPower");
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;

    public DodgeSucceedPower(AbstractCreature owner){
        this.name=NAME;
        this.ID=POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
    }
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0)
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        return damageAmount;
    }
    public void atStartOfTurn() {   //在下一个回合开始后自动消除
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        addToBot(new ApplyPowerAction(owner, owner, new ComboPurePower(owner)));
    }
}
