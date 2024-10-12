package theEnforcer.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class HypePowerCheckAction extends AbstractGameAction{

    private HypePower power;

    public HypePowerCheckAction(HypePower power) {
        this.power = power;
        this.source = power.owner;
        this.target = power.owner;
        this.amount = power.amount;
    }

    @Override
    public void update() {
        if (!this.power.gainedThisPlay){
            power.flash();
            Wiz.atb(new RemoveSpecificPowerAction(target, source, power));
        }
        this.isDone = true;
    }
    
}
