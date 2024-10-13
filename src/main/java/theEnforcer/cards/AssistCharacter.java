package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.ApplyAssistCharPower;
import theEnforcer.powers.AssistCharacterPower;
import theEnforcer.util.Wiz;

public class AssistCharacter extends AbstractEnforcerCard {
    public final static String ID = makeID(AssistCharacter.class.getSimpleName());

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    public AssistCharacter() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyAssistCharPower(p, p, new AssistCharacterPower(p, 1, PlayerClass.THE_SILENT, upgraded)));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
    }
}