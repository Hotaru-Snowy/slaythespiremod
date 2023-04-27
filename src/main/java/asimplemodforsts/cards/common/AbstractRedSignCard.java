package asimplemodforsts.cards.common;

import asimplemodforsts.powers.RedSignPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractRedSignCard extends AbstractSignCard {

    public AbstractRedSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.baseMagicNumber=1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RedSignPower(p, this.baseMagicNumber)));
    }
    @Override
    public void triggerOnGlowCheck() {
        //this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        this.glowColor = Color.RED.cpy();
    }
}
