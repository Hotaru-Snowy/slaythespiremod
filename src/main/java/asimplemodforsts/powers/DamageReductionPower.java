package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DamageReductionPower extends AbstractPower {
        public static final String POWER_ID = "DamageReductionPower";
        private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        public static final String NAME =POWER_STRINGS.NAME;
        public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;
        private  final int mitigation;
         //存在BUG，普通与PLUS一起使用时只会出现一种减伤，例如先50%，后使用plus70%，则会显示两层50%
        public DamageReductionPower(AbstractCreature owner,int mitigation){
            this.name=NAME;
            this.ID=POWER_ID;
            this.owner = owner;
            this.amount = 1;
            this.mitigation =mitigation; //n%减伤率,在最后return返回小数
            this.updateDescription();
            this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
            this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
        }
    //减伤
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * (100 - mitigation) / 100;
        } else {
            return damage;
        }
    }
    //无法从卡牌获得格挡
    public float modifyBlockLast(float blockAmount) {
        return 0.0F;
    }

    /*失去所有格挡，未测试
    public int onPlayerGainedBlock(int blockAmount) {
            return blockAmount;
        }
     */
    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "DamageReductionPower"));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, "DamageReductionPower", 1));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.mitigation + DESCRIPTIONS[1];
    }
}
