package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.actions.BurnLoseHpAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BurnPower extends AbstractPower {
        public static final String POWER_ID = "BurnPower";

        private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

        public static final String NAME = powerStrings.NAME;

        public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        private final AbstractCreature source;

        public BurnPower(AbstractCreature owner, AbstractCreature source, int burnAmt) {
            this.name = NAME;
            this.ID = POWER_ID;
            this.owner = owner;
            this.source = source;
            this.amount = burnAmt;
            if (this.amount >= 9999)
                this.amount = 9999;
            updateDescription();
            //loadRegion("burn");
            this.type = AbstractPower.PowerType.DEBUFF;
            this.isTurnBased = true;
            //此处使用的图标未改动，和其他一样
            region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
            region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
        }

        public void playApplyPowerSfx() {
            CardCrawlGame.sound.play("CARD_BURN", 0.05F);
        }

        public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }

        public void updateDescription() {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }

        @Override
        public void atEndOfRound() {
            if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                    !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                flashWithoutSound();
                addToBot(new BurnLoseHpAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
            }
            addToBot(new RemoveSpecificPowerAction(this.owner, this.source, "BurnPower"));
        }
}
