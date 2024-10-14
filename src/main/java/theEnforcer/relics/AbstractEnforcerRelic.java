package theEnforcer.relics;

import basemod.abstracts.CustomRelic;
import basemod.helpers.RelicType;
import theEnforcer.util.TexLoader;

import static theEnforcer.EnforcerCharacter.Enums.ENFORCER_BLACK;
import static theEnforcer.EnforcerMod.makeRelicPath;
import static theEnforcer.EnforcerMod.modID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractEnforcerRelic extends CustomRelic {
    public AbstractCard.CardColor color = null;
    public RelicType type = null;

    public AbstractEnforcerRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
        this(setId, tier, sfx, true);
    }

    public AbstractEnforcerRelic(String setId, RelicTier tier, LandingSound sfx, boolean isEnforcerRelic) {
        this(setId, tier, sfx, null);
        if (isEnforcerRelic){
            this.color = ENFORCER_BLACK;
        } else {
            type = RelicType.SHARED;
        }
    }
    
    public AbstractEnforcerRelic(String setId, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx, AbstractCard.CardColor color) {
        super(setId, TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + ".png")), tier, sfx);
        outlineImg = TexLoader.getTexture(makeRelicPath(setId.replace(modID + ":", "") + "Outline.png"));
        this.color = color;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}