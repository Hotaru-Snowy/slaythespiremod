package asimplemodforsts.actions;

import asimplemodforsts.cards.common.AbstractSignCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class ResetSignCardAction extends AbstractGameAction {
    private AbstractSignCard card;
    public ResetSignCardAction(AbstractSignCard c){
        card=c;
    }
    @Override
    public void update() {
    }
}
