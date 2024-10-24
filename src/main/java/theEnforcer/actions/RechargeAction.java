package theEnforcer.actions;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.RecycleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import theEnforcer.EnforcerMod;
import theEnforcer.util.Wiz;

public class RechargeAction extends AbstractGameAction{

    private AbstractPlayer p;
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(EnforcerMod.makeID(RechargeAction.class.getSimpleName()));
    public static final String[] TEXT = uiStrings.TEXT;

    public RechargeAction(int amount){
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = Wiz.adp();
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else if (this.p.hand.size() == 1) {
                if (this.p.hand.getBottomCard().costForTurn == -1) {
                    addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy() + this.amount));
                } else if (this.p.hand.getBottomCard().costForTurn > 0) {
                    addToTop(new DrawCardAction(this.p.hand.getBottomCard().cost + this.amount));
                }
                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                this.tickDuration();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    if (c.costForTurn == -1) {
                        addToTop(new DrawCardAction(EnergyPanel.getCurrentEnergy() + this.amount));
                    } else if (c.costForTurn <= 0 && this.amount > 0) {
                        addToTop(new DrawCardAction(this.amount));
                    } else {
                        addToTop(new DrawCardAction(c.cost + this.amount));
                    }
                    this.p.hand.moveToExhaustPile(c);
                }

                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }

            this.tickDuration();
        }
   }    
}
