package theEnforcer.relics;

import static theEnforcer.ModFile.makeID;

import theEnforcer.CharacterFile;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.ENFORCER_BLACK);
    }
}
