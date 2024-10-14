package theEnforcer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class MixUpAction extends AbstractGameAction{

    private DamageInfo info;

    public MixUpAction(AbstractCreature source, AbstractCreature target, DamageInfo damageInfo) {
        this.source = source;
        this.target = target;
        this.info = damageInfo;
    }

    public void update() {
        if (shouldCancelAction()){
            this.isDone = true;
            return;
        }
        tickDuration();

        if (this.isDone){
            if ((this.amount >= this.target.currentBlock) && this.target.currentBlock > 0) {
                Wiz.applyToSelfTop(new HypePower(this.source, 1));
            }
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_LIGHT, false));
            this.target.damage(info);

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()){
                AbstractDungeon.actionManager.clearPostCombatActions();
            } else {
                Wiz.att(new WaitAction(0.1f));
            }
        }
        this.isDone = true;
    }
    
}
