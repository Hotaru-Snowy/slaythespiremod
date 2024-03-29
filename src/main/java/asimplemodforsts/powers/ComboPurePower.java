package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

//纯色信号球，可以当作任意信号球使用
public class ComboPurePower extends AbstractPower {
    public static final String POWER_ID = "ComboPurePower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings("ComboPurePower");
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;

    public ComboPurePower(AbstractCreature owner){
        this.name=NAME;
        this.ID=POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0)
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
        return damageAmount;
    }
    public void atEndOfTurn(boolean isPlayer) {   //在回合结束后自动消除
        if(isPlayer)addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }
}
