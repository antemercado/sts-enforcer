package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.FinisherAction;

public class Finisher extends AbstractEnforcerCard {
    public final static String ID = makeID(Finisher.class.getSimpleName());

    private static final int COST = 3;
    private static final int DAMAGE = 26;
    private static final int UPGRADE_PLUS_DAMAGE = 8;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public Finisher() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FinisherAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), this));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}