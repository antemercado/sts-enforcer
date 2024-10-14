package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;
import static theEnforcer.util.Wiz.applyToEnemy;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class OverheadStrike extends AbstractEnforcerCard {
    public final static String ID = makeID(OverheadStrike.class.getSimpleName());

    private static final int COST = 2;
    private static final int DAMAGE = 13;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_DAMAGE = 5;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public OverheadStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}