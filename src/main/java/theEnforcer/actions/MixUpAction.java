package theEnforcer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class MixUpAction extends AbstractGameAction{

    public MixUpAction(AbstractCreature source, AbstractCreature target, int damage) {
        this.source = source;
        this.target = target;
        this.amount = damage;
    }

    public void update() {
        if (shouldCancelAction()){
            this.isDone = true;
            return;
        }
        tickDuration();

        if (this.isDone){
            if (this.amount >= this.target.currentBlock) {
                Wiz.applyToSelf(new HypePower(this.source, 1));
            }
            this.target.damage(new DamageInfo(source, amount));

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()){
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                Wiz.att(new WaitAction(0.1f));
            }
        }
        this.isDone = true;
    }
    
}
