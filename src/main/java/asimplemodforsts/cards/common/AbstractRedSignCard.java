package asimplemodforsts.cards.common;

import asimplemodforsts.powers.RedSignPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractRedSignCard extends AbstractSignCard {

    public AbstractRedSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.baseMagicNumber=1;
    }

    /**
     * 此处实现use方法
     * 如果实现信号球，请尽量不要复写这个方法，而是复写normalUse()和tripleSignUse()
     * 如有需要功能实现，可以调用super
     * @param p 玩家
     * @param m 目标怪物
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        //if(AbstractDungeon.player.hasPower("YellowSignPower"))AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "YellowSignPower"));
        //if(AbstractDungeon.player.hasPower("BlueSignPower"))AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "BlueSignPower"));
        if(AbstractDungeon.player.hasPower("ComboPurePower")){
            tripleSignUse(p,m);
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "ComboPurePower"));
            if(AbstractDungeon.player.hasPower("RedSignPower"))AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "RedSignPower"));
            return;
        }
        for(AbstractPower power : AbstractDungeon.player.powers){
            if(power.ID.equals("RedSignPower")  && power.amount==2){
                tripleSignUse(p,m);
                if(AbstractDungeon.player.hasPower("RedSignPower"))AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "RedSignPower"));
                return;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RedSignPower(p, this.baseMagicNumber)));
        normalUse(p,m);
    }

    /**
     * 如果达成三消条件就发光
     */
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(p.ID.equals("RedSignPower")  && p.amount==3 || p.ID.equals("ComboPurePower")){
                this.glowColor = Color.RED.cpy();
                break;
            }
        }
    }
}
