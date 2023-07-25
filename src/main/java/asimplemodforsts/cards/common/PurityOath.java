package asimplemodforsts.cards.common;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.actions.BurnAttackAction;
import asimplemodforsts.pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class PurityOath extends AbstractYellowSignCard {
    public static final String ID = "purity_oath";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASIC_DAMAGE = 6;
    private static final int UPGRADE_PLUS_DAMAGE = 2;
    private static final int BASIC_MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    public PurityOath() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = BASIC_DAMAGE;
        this.isMultiDamage = true;
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
        this.selfRetain = false;
    }

    @Override
    public void tripleSignUse(AbstractPlayer p, AbstractMonster m) {
        normalUse(p,m);
        addToBot(new HealAction(p, p, 2));
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnAttackAction(m,new DamageInfo(p,damage,damageTypeForTurn)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new PurityOath();
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
        }
    }
}
