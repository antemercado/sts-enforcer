package theEnforcer.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import theEnforcer.cards.AbstractEnforcerCard;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondDamage extends DynamicVariable {

    @Override
    public String key() {
        return makeID("sd");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).isSecondDamageModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractEnforcerCard) {
            ((AbstractEnforcerCard) card).isSecondDamageModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).secondDamage;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).baseSecondDamage;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).upgradedSecondDamage;
        }
        return false;
    }
}