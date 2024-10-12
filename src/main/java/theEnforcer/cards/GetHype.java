package theEnforcer.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.EnforcerMod;
import theEnforcer.enums.CustomTags;
import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class GetHype extends AbstractEnforcerCard{

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    
    public static final String ID = EnforcerMod.makeID(GetHype.class.getSimpleName());

    public GetHype() {
        super(ID, COST, TYPE, RARITY, TARGET);

        tags.add(CustomTags.HYPE_GEN);
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelfTop(new HypePower(p));
    }
    
}
