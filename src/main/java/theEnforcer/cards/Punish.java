package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.PunishPower;
import theEnforcer.util.Wiz;

public class Punish extends AbstractEnforcerCard {
    public final static String ID = makeID(Punish.class.getSimpleName());

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    public Punish() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new PunishPower(p));
    }

    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLOCK);
        upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
    }
}