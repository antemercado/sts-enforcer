package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class MeterManagement extends AbstractEnforcerCard {
    public final static String ID = makeID(MeterManagement.class.getSimpleName());

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    public MeterManagement() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.adp().hasPower(HypePower.POWER_ID)){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower(HypePower.POWER_ID)){
            return;
        }
        int hypeCount = p.getPower(HypePower.POWER_ID).amount;
        addToBot(new GainEnergyAction(hypeCount));
    }

    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}