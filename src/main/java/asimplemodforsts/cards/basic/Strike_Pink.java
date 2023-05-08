package asimplemodforsts.cards.basic;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Pink extends CustomCard {
    public static final String ID = "strike_pink";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String IMGPATH = ResourceLib.cardImagePath(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BASIC_DAMAGE = 6;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    public Strike_Pink() {
        super(ID, NAME, IMGPATH, COST, DESCRIPTION, CardType.ATTACK, AbstractCardEnum.Liv_COLOR, CardRarity.BASIC, CardTarget.ENEMY);
        tags.add(AbstractCard.CardTags.STRIKE);
        tags.add(AbstractCard.CardTags.STARTER_STRIKE);
        baseDamage = BASIC_DAMAGE;
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p,damage,DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new Strike_Pink();
    }
}
