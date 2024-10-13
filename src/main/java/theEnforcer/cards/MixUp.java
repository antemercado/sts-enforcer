package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.MixUpAction;
import theEnforcer.util.Wiz;

public class MixUp extends AbstractEnforcerCard {
    public final static String ID = makeID(MixUp.class.getSimpleName());

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public MixUp() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MixUpAction(p, m, this.damage));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}