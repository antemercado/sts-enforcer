package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;

import theEnforcer.powers.AssistCharacterPower;
import theEnforcer.util.Wiz;

@AutoAdd.Ignore
public class AssistCharHelperCard extends CustomCard {
    public final static String ID = makeID(AssistCharHelperCard.class.getSimpleName());

    private static final int COST = -2;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;

    private AbstractPlayer assistPlayer;

    public AssistCharHelperCard(AbstractPlayer player, String img) {
        super(ID,
            languagePack.getCardStrings(ID).NAME + player.getLocalizedCharacterName(),
            img,
            COST,
            languagePack.getCardStrings(ID).DESCRIPTION + player.getLocalizedCharacterName(),
            TYPE,
            player.getCardColor(),
            RARITY,
            TARGET);

            this.assistPlayer = player;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption(){
        
        Wiz.applyToSelf(new AssistCharacterPower(Wiz.adp(), this.assistPlayer, this.upgraded));
    }

    @Override
    public void upgrade() {
        this.upgraded = true;
    }
}