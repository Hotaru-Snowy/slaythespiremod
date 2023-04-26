package asimplemodforsts.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public abstract class AbstractRedSignCard extends AbstractSignCard {

    public AbstractRedSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        if(directly){
            ArrayList<AbstractSignCard> saves = new ArrayList<>();
            boolean find = false;
            for(AbstractCard c : p.hand.group){
                if(c instanceof AbstractSignCard){
                    if(saves.size()==3){
                        if(find)break;
                        saves.clear();
                    }
                    saves.add((AbstractSignCard)c);
                    if(c==this)find=true;
                }else if(find)break;
                else saves.clear();
            }
            for(AbstractSignCard c : saves){
                c.directly=false;
                if(c!=this)AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(c, m, false, true));
            }
        }
    }
    @Override
    public void triggerOnGlowCheck() {
        //this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        this.glowColor = Color.RED.cpy();
    }
}
