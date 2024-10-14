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

public class AdrenoTrigger extends CustomRelic {

    public static final String ID = EnforcerMod.makeID(AdrenoTrigger.class.getSimpleName());

    private static final Texture IMG = TexLoader.getTexture(makeRelicPath("TodoItem.png"));
    private static final Texture OUTLINE = TexLoader.getTexture(makeRelicOutlinePath("TodoItem.png"));

    public AdrenoTrigger() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.CLINK);
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

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
