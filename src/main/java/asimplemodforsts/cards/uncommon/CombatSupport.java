package asimplemodforsts.cards.uncommon;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.powers.CombatSupportPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CombatSupport extends CustomCard {
    public static final String ID = "combat_support";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int BASIC_MAGIC = 3;
    private static final int UPGRADE_PLUS_MAGIC = -1;
    public CombatSupport(){
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.POWER, AbstractCardEnum.Liv_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CombatSupportPower(p, upgraded), upgraded?2:3));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new CombatSupport();
    }
}
