package theEnforcer.modifiers.damageMods;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.core.AbstractCreature;

import theEnforcer.EnforcerMod;

public class IgnoresBlockMod extends AbstractDamageModifier{

    public static String ID = EnforcerMod.makeID(IgnoresBlockMod.class.getSimpleName());

    public IgnoresBlockMod(){
    }

    @Override
    public boolean isInherent() {
        return true;
    }

    @Override
    public boolean ignoresBlock(AbstractCreature target) {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new IgnoresBlockMod();
    }
    
}
