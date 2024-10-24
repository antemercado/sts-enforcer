package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.RewireAction;
import theEnforcer.cards.helpers.RewireHelper;

public class Rewire extends AbstractEnforcerCard {
    public final static String ID = makeID(Rewire.class.getSimpleName());

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_PLUS_MAGIC = 1;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    public Rewire() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractCard> cardsToModify = new ArrayList<>();

        for (int i=0; i < p.hand.size(); i++){
            if (p.hand.group.get(i).equals(this)){
                if ( i != 0 ){
                    cardsToModify.add(p.hand.group.get(i - 1));
                }
                if ( i != p.hand.size() -1 ){
                    cardsToModify.add(p.hand.group.get(i + 1));
                }
            }
        }
        ArrayList<AbstractCard> cardChoices = new ArrayList<>();

        cardChoices.add(new RewireHelper(this.magicNumber * -1, cardsToModify));
        cardChoices.add(new RewireHelper(this.magicNumber, cardsToModify));

        addToBot(new ChooseOneAction(cardChoices));
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
    }
}