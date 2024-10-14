package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.SweetChinAction;

public class SweetChinMusic extends AbstractEnforcerCard {
    public final static String ID = makeID(SweetChinMusic.class.getSimpleName());

    private static final int COST = 2;
    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DAMAGE = 5;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    public SweetChinMusic() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SweetChinAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}