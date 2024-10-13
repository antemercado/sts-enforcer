package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import basemod.AutoAdd;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class c50Block extends AbstractEnforcerCard {
    public final static String ID = makeID(c50Block.class.getSimpleName());

    private static final int COST = -2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    public c50Block() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseBlock = this.block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        blck();
    }

    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
}