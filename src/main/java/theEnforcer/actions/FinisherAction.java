package theEnforcer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FinisherAction extends AbstractGameAction{

    private DamageInfo info;
    private AbstractCard cardToExhaust;

    public FinisherAction(AbstractCreature target, DamageInfo info, AbstractCard card){
        this.info = info;
        this.cardToExhaust = card;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1f;
    }

    @Override
    public void update() {
        if (this.duration == 0.1f && this.target != null){
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                !this.target.hasPower(MinionPower.POWER_ID)) {
                    
                } else {
                    addToTop(new ExhaustSpecificCardAction(this.cardToExhaust, null));
                }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
    
}
