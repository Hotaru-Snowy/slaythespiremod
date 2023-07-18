package asimplemodforsts.cards.common;

import asimplemodforsts.utils.GlobalList;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractSignCard extends CustomCard {
    final String SIGNCOLOR;
    final String SIGNPOWER;
    public AbstractSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,String signcolor) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        SIGNCOLOR=signcolor;
        SIGNPOWER=SIGNCOLOR+"SignPower";
        GlobalList.SignColorList.add(SIGNCOLOR);
        this.selfRetain = true;
    }
    /**
     * 三消时调用这个方法
     * @param p 玩家
     * @param m 目标怪物
     */
    public abstract void tripleSignUse(AbstractPlayer p, AbstractMonster m);

    /**
     * 正常调用这个方法
     * @param p 玩家
     * @param m 目标怪物
     */
    public abstract void normalUse(AbstractPlayer p, AbstractMonster m);

    /**
     * 达成三消条件发出对应的光
     */
    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(p.ID.equals(SIGNPOWER)  && p.amount==3 || p.ID.equals("ComboPurePower")){
                try {
                    this.glowColor = (Color) Color.class.getDeclaredField(SIGNCOLOR.toUpperCase()).get(Color.class);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}
