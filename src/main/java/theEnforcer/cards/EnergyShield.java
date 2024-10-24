package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.EnergyShieldPower;
import theEnforcer.util.Wiz;

public class EnergyShield extends AbstractEnforcerCard {
    public final static String ID = makeID(EnergyShield.class.getSimpleName());

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    public EnergyShield() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new EnergyShieldPower(p, 1));        
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}