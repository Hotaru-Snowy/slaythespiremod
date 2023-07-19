package asimplemodforsts.cards.rare;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.utils.GlobalList;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SignalFill extends CustomCard {
    public static final String ID = "signal_fill";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    public SignalFill() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.RARE, CardTarget.SELF);
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(String color : GlobalList.SignColorList){
            if(p.hasPower(color+"SignPower")){
                try {
                    addToBot(new ApplyPowerAction(p, p,
                            (AbstractPower)Class.forName("asimplemodforsts.powers."+color+"SignPower").getConstructor(AbstractCreature.class,int.class).newInstance(p,1)
                    ));
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        canUse=false;
        for(String color : GlobalList.SignColorList){
            if(p.hasPower(color+"SignPower") && p.getPower(color+"SignPower").amount==1){
                canUse=true;
                break;
            }
        }
        return canUse;
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new SignalFill();
    }
}
