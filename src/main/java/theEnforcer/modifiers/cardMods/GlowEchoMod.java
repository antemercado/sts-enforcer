// Copied from Diamsword's code from Packmaster Mod
package theEnforcer.modifiers.cardMods;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.AbstractCardModifier;
import theEnforcer.EnforcerMod;

public class GlowEchoMod extends AbstractCardModifier {
    public static String ID = EnforcerMod.makeID(GlowEchoMod.class.getSimpleName());
    public AbstractCardModifier makeCopy() {
        return new GlowEchoMod();
    }
    public String identifier(AbstractCard card) {
        return ID;
    }
}
