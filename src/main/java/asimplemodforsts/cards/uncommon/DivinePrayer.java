package asimplemodforsts.cards.uncommon;

import asimplemodforsts.Main;
import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.powers.DamageReductionPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DivinePrayer extends CustomCard {
    public static final String ID = "divine_prayer";
    public static final CardStrings cardstrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardstrings.NAME;
    public static final String IMGPATH = ResourceLib.cardImagePath(ID);
    public static final String DESCRIPTION = cardstrings.DESCRIPTION;
    public static final int COST = 2;
    private static final int BASIC_MAGIC = 50;   //代表50%
    private static final int UPGRADE_PLUS_MAGIC = 20;

    public DivinePrayer() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void upgrade() {
            if(!upgraded){
                upgradeName();
                upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            }
    }

    //需改成信号use使用，这里暂做测试使用
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveAllBlockAction(p,p));
        AbstractPower ap = p.getPower("DamageReductionPower");
        if(ap!=null){
            if(upgraded)((DamageReductionPower)ap).upgrad();
            Main.log("upgrad");
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new DamageReductionPower(p,upgraded)));
            Main.log("normal use");
        }
    }

    @Override
    public AbstractCard makeCopy(){
        return new DivinePrayer();
    }
}
