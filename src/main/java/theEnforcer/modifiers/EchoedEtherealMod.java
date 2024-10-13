// Copied from Diamsword's code from Packmaster Mod
package theEnforcer.modifiers;

import org.apache.commons.lang3.StringUtils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import basemod.abstracts.AbstractCardModifier;
import theEnforcer.EnforcerMod;

public class EchoedEtherealMod extends AbstractCardModifier {
    public static String ID = EnforcerMod.makeID(EchoedEtherealMod.class.getSimpleName());

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " " + StringUtils.capitalize(GameDictionary.ETHEREAL.NAMES[0]) + (Settings.lineBreakViaCharacter ? " " : "") + LocalizedStrings.PERIOD;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.isEthereal = true;
    }

    public void onRemove(AbstractCard card) {
        card.isEthereal = false;
    }

    public AbstractCardModifier makeCopy() {
        return new EchoedEtherealMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
