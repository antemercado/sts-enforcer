package theEnforcer.relics;

import basemod.abstracts.CustomRelic;
import theEnforcer.util.TexLoader;

import static theEnforcer.EnforcerMod.makeRelicPath;
import static theEnforcer.EnforcerMod.modID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractEasyRelic extends CustomRelic {
    public AbstractCard.CardColor color;

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, null);
    }

    public AbstractEasyRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        this.color = color;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}