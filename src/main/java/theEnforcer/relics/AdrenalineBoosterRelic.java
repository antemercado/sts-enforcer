package theEnforcer.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static theEnforcer.EnforcerMod.makeRelicOutlinePath;
import static theEnforcer.EnforcerMod.makeRelicPath;

import basemod.abstracts.CustomRelic;
import theEnforcer.EnforcerMod;
import theEnforcer.powers.HypePower;
import theEnforcer.util.TexLoader;
import theEnforcer.util.Wiz;

public class AdrenalineBoosterRelic extends CustomRelic {

    public static final String ID = EnforcerMod.makeID(AdrenalineBoosterRelic.class.getSimpleName());

    private static final Texture IMG = TexLoader.getTexture(makeRelicPath("TodoItem.png"));
    private static final Texture OUTLINE = TexLoader.getTexture(makeRelicOutlinePath("TodoItem.png"));

    public AdrenalineBoosterRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractPlayer p = Wiz.adp();
        Wiz.applyToSelf(new HypePower(p, 1));
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
