package asimplemodforsts.cards.rare;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.actions.BurnDamageAllEnemiesAction;
import asimplemodforsts.cards.common.AbstractRedSignCard;
import asimplemodforsts.pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChaosOath extends AbstractRedSignCard {
    public static final String ID = "chaos_oath";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int BASIC_DAMAGE = 18;
    private static final int UPGRADE_PLUS_DAMAGE = 8;
    private static final int BASIC_MAGIC = 5;
  //  private static final int UPGRADE_PLUS_MAGIC = 1;
    public ChaosOath() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Liv_COLOR, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = BASIC_DAMAGE;
        this.isMultiDamage = true;
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void tripleSignUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BurnDamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        addToBot(new DamageAction(p,new DamageInfo(p,baseMagicNumber)));
    }


    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot(new DamageAction(p,new DamageInfo(p,baseMagicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new ChaosOath();
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }
}
