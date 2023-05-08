package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;

public class DodgePower extends AbstractPower {
    public static final String POWER_ID = "DodgePower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings("DodgePower");
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;
    private static final int ComboPAmt = 1;//纯色信号球数量

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
            addToTop((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            //创建纯色信号球
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new ComboPurePower((AbstractCreature)this.owner, ComboPAmt), ComboPAmt));
        }
        return 0;
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atStartOfTurn() {   //在下一个回合开始后自动消除
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, ID));
    }
}
