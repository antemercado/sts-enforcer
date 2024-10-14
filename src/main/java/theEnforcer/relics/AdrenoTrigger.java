package theEnforcer.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static theEnforcer.EnforcerMod.makeRelicOutlinePath;
import static theEnforcer.EnforcerMod.makeRelicPath;

import basemod.abstracts.CustomRelic;
import theEnforcer.EnforcerMod;
import theEnforcer.util.TexLoader;
import theEnforcer.util.Wiz;

public class AdrenoTrigger extends AbstractEnforcerRelic {

    public static final String ID = EnforcerMod.makeID(AdrenoTrigger.class.getSimpleName());

    public AdrenoTrigger() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK);
    }

    @Override
    public void obtain() {
        if (Wiz.adp().hasRelic(AdrenalineBoosterRelic.ID)){
            for (AbstractRelic r : Wiz.adp().relics){
                if (r.relicId.equals(AdrenalineBoosterRelic.ID)){
                    instantObtain(Wiz.adp(), Wiz.adp().relics.indexOf(r), true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return Wiz.adp().hasRelic(AdrenalineBoosterRelic.ID);
    }

}
