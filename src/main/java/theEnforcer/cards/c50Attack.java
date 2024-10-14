package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import basemod.AutoAdd;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class c50Attack extends AbstractEnforcerCard {
    public final static String ID = makeID(c50Attack.class.getSimpleName());

    private static final int COST = -2;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private AbstractMonster enemyTarget;

    public c50Attack(int damage, AbstractMonster m) {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = damage;
        this.enemyTarget = m;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }
    
    @Override
    public void onChoseThisOption() {
        dmg(this.enemyTarget, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
    }
}