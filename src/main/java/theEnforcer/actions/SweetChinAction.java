package theEnforcer.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import theEnforcer.util.Wiz;

public class SweetChinAction extends AbstractGameAction{

    private DamageInfo info;

    public SweetChinAction(AbstractMonster target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1f;
    }

    // @Override
    // public void update() {
    //     if (shouldCancelAction()){
    //         this.isDone = true;
    //         return;
    //     }
    //     tickDuration();

    //     if (this.isDone){
    //         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
            
    //         this.target.damage(this.info);
    //         if (this.target.lastDamageTaken > 0){
    //             addToTop(new StunMonsterAction((AbstractMonster) this.target, this.source));
    //         }

    //         if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
    //             AbstractDungeon.actionManager.clearPostCombatActions();
    //         } else {
    //             addToTop(new WaitAction(0.1f));
    //         }
    //     }
    // }
    @Override
    public void update(){
        if (this.duration == 0.1f && this.target != null){
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0) {
                addToTop(new StunMonsterAction((AbstractMonster) this.target, this.source));
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
