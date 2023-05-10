package asimplemodforsts.cards.basic;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import asimplemodforsts.powers.DodgePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Dodge_Pink extends CustomCard {
    public static final String ID = "dodge_pink";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;

    public Dodge_Pink() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Liv_COLOR, CardRarity.BASIC, CardTarget.SELF);
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DodgePower(p)));
        addToBot(new PressEndTurnButtonAction());
    }

}
