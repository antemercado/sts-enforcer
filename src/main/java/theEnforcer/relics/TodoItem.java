package theEnforcer.relics;

import static theEnforcer.EnforcerMod.makeID;

import theEnforcer.EnforcerCharacter;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, EnforcerCharacter.Enums.ENFORCER_BLACK);
    }
}
