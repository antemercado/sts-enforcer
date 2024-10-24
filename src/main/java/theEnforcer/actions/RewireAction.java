package theEnforcer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import theEnforcer.util.Wiz;

public class RewireAction extends AbstractGameAction{

    private AbstractCard card1 = null;
    private AbstractCard card2 = null;

    public RewireAction(AbstractCard card1, AbstractCard card2){
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = Wiz.adp();
        this.card1 = card1;
        this.card2 = card1;
    }

    @Override
    public void update() {

    }
    
}
