package theEnforcer.patches;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import theEnforcer.powers.HypePower;

@SpirePatch(
    clz = AbstractCard.class,
    method = "freeToPlay"
)
public class HypeFreeToPlayPatch {

    @SpirePrefixPatch
    public static SpireReturn FreePlay(AbstractCard c){
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
        (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT){
            if (AbstractDungeon.player.hasPower(HypePower.POWER_ID)){
                int hypeStacks = AbstractDungeon.player.getPower(HypePower.POWER_ID).amount;
                if (c.cost == hypeStacks){
                    return SpireReturn.Return(true);
                }
            }
        }
        return SpireReturn.Continue();
    }
    
}
