package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DamageReductionPower extends AbstractPower {
    public static final String POWER_ID = "DamageReductionPower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS = POWER_STRINGS.DESCRIPTIONS;
    private final int mitigation = 50;
    private final int mitigationP = 70;
    private boolean PLUS;

    public DamageReductionPower(AbstractCreature owner, boolean upgraded) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        amount = -1;
        PLUS = upgraded;
        updateDescription();
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
    }
    public void upgrad(){
        PLUS=true;
        updateDescription();
        if ((AbstractDungeon.getCurrRoom()).monsters != null)
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters)
                m.applyPowers();
    }
    //减伤
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * (100 - (PLUS ? mitigationP : mitigation)) / 100;
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
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "DamageReductionPower"));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (PLUS ? mitigationP : mitigation) + DESCRIPTIONS[1];
    }
}
