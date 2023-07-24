package asimplemodforsts.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class BurnLoseHpAction extends AbstractGameAction{
        private static final float DURATION = 0.33F;
        public BurnLoseHpAction(AbstractCreature target, AbstractCreature source, int amount, AbstractGameAction.AttackEffect effect) {
            this.setValues(target, source, amount);
            this.actionType = ActionType.DAMAGE;
            this.attackEffect = effect;
            this.duration = 0.33F;
        }

        public void update() {
            if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
                this.isDone = true;
            } else {
                if (this.duration == 0.33F && this.target.currentHealth > 0) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                }

                this.tickDuration();
                if (this.isDone) {
                    if (this.target.currentHealth > 0) {
                        this.target.tint.color = Color.CHARTREUSE.cpy();
                        this.target.tint.changeColor(Color.WHITE.cpy());
                        this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.NORMAL));
//                        if (this.target.isDying) {
//                            ++AbstractPlayer.poisonKillCount;
//                            if (AbstractPlayer.poisonKillCount == 3 && AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
//                                UnlockTracker.unlockAchievement("PLAGUE");
//                            }
//                        }
                    }

                    AbstractPower p = this.target.getPower("Burn");
                    if (p != null) {
                        --p.amount;
                        if (p.amount == 0) {
                            this.target.powers.remove(p);
                        } else {
                            p.updateDescription();
                        }
                    }

                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }

                    this.addToTop(new WaitAction(0.1F));
                }

            }
        }
}
