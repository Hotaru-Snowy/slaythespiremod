package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HealPointPower extends AbstractPower {
    public static final String POWER_ID = "HealPointPower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;
    public HealPointPower(AbstractCreature o,int n){
        name=NAME;
        ID=POWER_ID;
        owner=o;
        amount=n;
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
        updateDescription();
    }
    @Override
    public int onHeal(int healAmount){
        int t = healAmount-owner.maxHealth+owner.currentHealth;
        if(t>0)
            addToBot(new GainBlockAction(owner, owner, t*2));
        addToBot(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        return healAmount;
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
