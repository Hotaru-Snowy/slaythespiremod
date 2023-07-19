package asimplemodforsts.powers;

import asimplemodforsts.ResourceLib;
import asimplemodforsts.cards.common.AbstractSignCard;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public class CombatSupportPower extends AbstractPower {
    public static final String POWER_ID = "CombatSupportPower";
    private static final PowerStrings POWER_STRINGS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME =POWER_STRINGS.NAME;
    public static final String[] DESCRIPTIONS=POWER_STRINGS.DESCRIPTIONS;
    private final boolean PLUS;
    private static int idOffset;
    public CombatSupportPower(AbstractCreature o,boolean p){
        PLUS=p;
        name=p?NAME+"+":NAME;
        ID=POWER_ID+idOffset;
        idOffset++;
        owner = o;
        amount = p?2:3;
        type=PowerType.BUFF;
        updateDescription();
        region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImagePath(POWER_ID)), 0, 0, 32, 32);
        region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ResourceLib.powerImageLargePath(POWER_ID)), 0, 0, 64, 64);
    }
    public void onAfterCardPlayed(AbstractCard card) {
        if(card instanceof AbstractSignCard)return;
        if(card.type == AbstractCard.CardType.ATTACK && owner instanceof AbstractPlayer){
            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                if(amount>1) addToBot(new ReducePowerAction(owner, owner, this, 1));
                else{
                    List<AbstractSignCard> lsc = new ArrayList<>();
                    AbstractPlayer p = (AbstractPlayer)owner;
                    for(AbstractCard c : p.hand.group){
                        if(c instanceof AbstractSignCard)lsc.add((AbstractSignCard) c);
                    }
                    for(AbstractCard c : p.drawPile.group){
                        if(c instanceof AbstractSignCard)lsc.add((AbstractSignCard) c);
                    }
                    for(AbstractCard c : p.discardPile.group){
                        if(c instanceof AbstractSignCard)lsc.add((AbstractSignCard) c);
                    }
                    if(!lsc.isEmpty()){
                        AbstractSignCard tc = lsc.get(new RandomXS128().nextInt(lsc.size()));
                        try {
                            Thread.sleep(1L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        addToBot(new MakeTempCardInHandAction(tc.makeStatEquivalentCopy(), 1));
                    }
                    addToBot(new ApplyPowerAction(owner, owner, this, (PLUS?2:3)-amount));
                }
            }
        }
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+(PLUS?2:3)+DESCRIPTIONS[1];
    }
}
