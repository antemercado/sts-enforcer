package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class Setup extends AbstractEnforcerCard {
    public final static String ID = makeID(Setup.class.getSimpleName());
    
    private static final int COST = 2;
    private static final int MAGIC = 2;
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    public Setup() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(this.magicNumber));
        addToBot(new DrawCardAction(this.magicNumber));
        Wiz.applyToSelf(new HypePower(p));
    }

    public void upp() {
        this.exhaust = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}