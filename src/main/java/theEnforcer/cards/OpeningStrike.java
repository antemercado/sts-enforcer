package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class OpeningStrike extends AbstractEnforcerCard {
    public final static String ID = makeID(OpeningStrike.class.getSimpleName());

    private static final int COST = 0;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DAMAGE = 4;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public OpeningStrike() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.isInnate = true;
        this.exhaust = true;
        this.baseDamage = this.damage = DAMAGE;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelfTop(new HypePower(p));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}