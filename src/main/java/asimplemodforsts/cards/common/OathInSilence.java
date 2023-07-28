package asimplemodforsts.cards.common;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class OathInSilence extends AbstractYellowSignCard {
    public static final String ID = "oath_in_silence";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASIC_MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;
    public OathInSilence() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = BASIC_MAGIC;
        magicNumber = baseMagicNumber;
    }

    @Override
    public void tripleSignUse(AbstractPlayer p, AbstractMonster m) {
        normalUse(p,m);
        addToBot(new ApplyPowerAction( AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void normalUse(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
        }
    }
}
