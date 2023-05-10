package asimplemodforsts.cards.common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractSignCard extends CustomCard {
    public AbstractSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
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
}
