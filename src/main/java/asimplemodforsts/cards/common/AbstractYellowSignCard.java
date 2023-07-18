package asimplemodforsts.cards.common;

import asimplemodforsts.powers.YellowSignPower;
import asimplemodforsts.utils.GlobalList;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractYellowSignCard extends AbstractSignCard{
    public AbstractYellowSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target,"Yellow");
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
        for(String colors : GlobalList.SignColorList)
            if(!colors.equalsIgnoreCase(SIGNCOLOR))
                if(p.hasPower(colors+"SignPower"))
                    addToBot(new RemoveSpecificPowerAction(p, p, colors+"SignPower"));
        if(p.hasPower("ComboPurePower")){
            tripleSignUse(p,m);
            addToBot(new RemoveSpecificPowerAction(p, p, "ComboPurePower"));
            if(p.hasPower(SIGNPOWER))addToBot(new RemoveSpecificPowerAction(p, p, SIGNPOWER));
            return;
        }
        for(AbstractPower power : p.powers){
            if(power.ID.equals(SIGNPOWER)  && power.amount==2){
                tripleSignUse(p,m);
                if(p.hasPower(SIGNPOWER))addToBot(new RemoveSpecificPowerAction(p, p, SIGNPOWER));
                return;
            }
        }
        addToBot(new ApplyPowerAction(p, p, new YellowSignPower(p, 1)));
        normalUse(p,m);
    }
}
