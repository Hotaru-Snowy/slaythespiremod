package asimplemodforsts.cards.common;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BlackHole extends AbstractYellowSignCard{
    public static final String ID = "black_hole";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASIC_DAMAGE = 4;
    private static final int UPGRADE_PLUS_DAMAGE = 1;
    private static final int BASIC_MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    public BlackHole(){
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Liv_COLOR, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = BASIC_DAMAGE;
        this.isMultiDamage = true;
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
    }
    @Override
    public void tripleSignUse(AbstractPlayer p, AbstractMonster m) {
        normalUse(p,m);
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new BlackHole();
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
