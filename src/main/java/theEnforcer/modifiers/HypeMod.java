package theEnforcer.modifiers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import basemod.abstracts.AbstractCardModifier;
import theEnforcer.EnforcerMod;
import theEnforcer.enums.CustomTags;
import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class HypeMod extends AbstractCardModifier{

    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(EnforcerMod.makeID(HypeMod.class.getSimpleName()));

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + uiStrings.TEXT[0];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.applyToSelfTop(new HypePower(Wiz.adp()));
        card.tags.add(CustomTags.HYPE_GEN);
    }

    @Override
    public void onRemove(AbstractCard card) {
        card.tags.removeIf(p -> p.equals(CustomTags.HYPE_GEN));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new HypeMod();
    }
    
}
