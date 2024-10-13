package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import theEnforcer.util.Wiz;

public class EveningElephant extends AbstractEnforcerCard {
    public final static String ID = makeID(EveningElephant.class.getSimpleName());

    private static final int COST = 2;
    private static final int DAMAGE = 18;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_DAMAGE = 7;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public EveningElephant() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        Wiz.applyToSelf(new WeakPower(p, this.magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}