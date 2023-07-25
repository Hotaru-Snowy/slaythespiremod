package asimplemodforsts.actions;

import asimplemodforsts.powers.BurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class BurnAttackAction extends AbstractGameAction {
    private DamageInfo info;
    public BurnAttackAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        actionType = AbstractGameAction.ActionType.DAMAGE;
        startDuration = Settings.ACTION_DUR_FAST;
        duration = this.startDuration;
    }
    @Override
    public void update() {
        if (shouldCancelAction()) {
            isDone = true;
            return;
        }
        tickDuration();
        if (isDone) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.FIRE, false));
            target.damage(info);
            if (target.lastDamageTaken > 0 && !target.isDead)
                addToTop(new ApplyPowerAction(target, source, new BurnPower(target,source, target.lastDamageTaken/2), target.lastDamageTaken/2));
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
            else addToTop(new WaitAction(0.1F));
        }
    }
}