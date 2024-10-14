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

public class AdrenalineBoosterRelic extends AbstractEnforcerRelic {

    public static final String ID = EnforcerMod.makeID(AdrenalineBoosterRelic.class.getSimpleName());

    public AdrenalineBoosterRelic() {
        super(ID, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
        AbstractPlayer p = Wiz.adp();
        Wiz.applyToSelfTop(new HypePower(p));
    }

}
