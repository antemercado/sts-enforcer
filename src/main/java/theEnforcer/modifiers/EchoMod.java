// Copied from Diamsword's code from Packmaster Mod
package theEnforcer.modifiers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import theEnforcer.EnforcerMod;

public class EchoMod extends AbstractCardModifier{
    public static String ID = EnforcerMod.makeID(EchoMod.class.getSimpleName());

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractCard c = card.makeCopy();
        if (card.upgraded){
            c.upgrade();
        }

        CardModifierManager.addModifier(c, new ExhaustMod());
        CardModifierManager.addModifier(c, new EchoedEtherealMod());
        CardModifierManager.addModifier(c, new GlowEchoMod());

        addToBot(new MakeTempCardInHandAction(c));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new EchoMod();
    }
    
}
