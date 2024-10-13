package theEnforcer.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import theEnforcer.powers.AssistCharacterPower;
import theEnforcer.util.Wiz;

public class ApplyAssistCharPower extends ApplyPowerAction{

    private AssistCharacterPower assistPower;
    public static final Logger logger = LogManager.getLogger(ApplyAssistCharPower.class.getName());

    public ApplyAssistCharPower(AbstractCreature target, AbstractCreature source, AssistCharacterPower powerToApply) {
        super(target, source, powerToApply);
        this.assistPower = powerToApply;
    }

    @Override
    public void update(){
        if (this.target.hasPower(AssistCharacterPower.POWER_ID)){
            AssistCharacterPower po = (AssistCharacterPower)target.getPower(AssistCharacterPower.POWER_ID);
            logger.info("Adding assistPower.playerClass to array: " + assistPower.playerClass);
            po.classArray.add(assistPower.playerClass);
            po.upgradedArray.add(assistPower.upgraded);
        }
        super.update();
    }
    
}
