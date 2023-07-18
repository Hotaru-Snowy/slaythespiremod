package asimplemodforsts.cards.common;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.powers.HealPointPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LivShield extends AbstractBlueSignCard{
    public static final String ID = "liv_shield";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASIC_BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public LivShield(){
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.COMMON, CardTarget.SELF);
        baseBlock=BASIC_BLOCK;
    }

    @Override
    public void tripleSignUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HealPointPower(p, this.block)));
        addToBot(new HealAction(p, p, block));
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new LivShield();
    }
}
