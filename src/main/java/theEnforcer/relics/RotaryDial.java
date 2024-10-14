package theEnforcer.relics;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static theEnforcer.EnforcerMod.makeRelicOutlinePath;
import static theEnforcer.EnforcerMod.makeRelicPath;

import basemod.abstracts.CustomRelic;
import basemod.helpers.CardModifierManager;
import theEnforcer.EnforcerMod;
import theEnforcer.enums.CustomTags;
import theEnforcer.modifiers.HypeMod;
import theEnforcer.util.TexLoader;
import theEnforcer.util.Wiz;

public class RotaryDial extends CustomRelic implements
    OnCreateCardInterface {

    public static final String ID = EnforcerMod.makeID(RotaryDial.class.getSimpleName());

    private static final Texture IMG = TexLoader.getTexture(makeRelicPath("TodoItem.png"));
    private static final Texture OUTLINE = TexLoader.getTexture(makeRelicOutlinePath("TodoItem.png"));

    public RotaryDial() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public void atPreBattle() {
        boolean shouldFlash = false;
        for (AbstractCard c: Wiz.adp().drawPile.group){
            if (c.hasTag(CustomTags.HYPE_GEN)){
                continue;
            }
            if (c.hasTag(CardTags.STRIKE)){
                shouldFlash = true;
                CardModifierManager.addModifier(c, new HypeMod());
            }
        }
        if (shouldFlash){
            flash();
        }
    }

    @Override
    public void onCreateCard(AbstractCard c) {
        if (c.hasTag(CustomTags.HYPE_GEN)){
            return;
        }
        if (!c.hasTag(CardTags.STRIKE)){
            return;
        }
        flash();
        CardModifierManager.addModifier(c, new HypeMod());
    }

    

    // All Strikes have Get Hyped
    
}
