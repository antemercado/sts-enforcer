package theEnforcer.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import theEnforcer.cards.AbstractEnforcerCard;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("m2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractEnforcerCard) {
            ((AbstractEnforcerCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractEnforcerCard) {
            return ((AbstractEnforcerCard) card).upgradedSecondMagic;
        }
        return false;
    }
}