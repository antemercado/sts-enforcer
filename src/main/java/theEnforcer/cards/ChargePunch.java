package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChargePunch extends AbstractEnforcerCard {
    public final static String ID = makeID(ChargePunch.class.getSimpleName());

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DAMAGE = 1;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;


    public ChargePunch() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1; i++){
            dmg(m, AbstractGameAction.AttackEffect.LIGHTNING);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int cardsPlayed = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        this.rawDescription = DESCRIPTION;
        if (cardsPlayed == 1){
            this.rawDescription += EXTENDED_DESCRIPTION[0];
        } else if (cardsPlayed > 1){
            this.rawDescription += EXTENDED_DESCRIPTION[1];
        }
        this.baseMagicNumber = this.magicNumber = cardsPlayed;
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}