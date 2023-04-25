package asimplemodforsts.cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractSignCard extends CustomCard {
    public boolean directly = true;
    public AbstractSignCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
    @Override
    public void didDiscard(){
        directly=true;
    }

}
