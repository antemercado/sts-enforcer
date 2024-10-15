package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.RunawayTrainPower;
import theEnforcer.util.Wiz;

public class RunawayTrain extends AbstractEnforcerCard {
    public final static String ID = makeID(RunawayTrain.class.getSimpleName());

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    public RunawayTrain() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new RunawayTrainPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
    }
}