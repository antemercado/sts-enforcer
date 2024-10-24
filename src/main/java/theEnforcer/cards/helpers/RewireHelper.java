package theEnforcer.cards.helpers;
import theEnforcer.EnforcerCharacter;
import theEnforcer.cards.AbstractEnforcerCard;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;

import static theEnforcer.EnforcerMod.makeID;
import static theEnforcer.EnforcerMod.modID;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore
public class RewireHelper extends AbstractEnforcerCard {
    public final static String ID = makeID(RewireHelper.class.getSimpleName());

    private static final int COST = -2;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;

    private int costMod = 0;
    private int resourceIndex = 0;

    private ArrayList<AbstractCard> cardsToModify;

    public RewireHelper(int costMod, ArrayList<AbstractCard> cardsToModify) {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.cardsToModify = cardsToModify;
        this.costMod = costMod;

        increaseOrDecrease();
    }
    
    public void increaseOrDecrease(){
        if (this.costMod < 0){
            this.resourceIndex = 0; // Decrease
        }
        if (this.costMod > 0){
            this.resourceIndex = 1; // Increase
        }
        
        // Image Selection
        this.textureImg = getCardTextureString((cardID + "_" + this.resourceIndex).replace(modID + ":", ""), type);
        loadCardImage(this.textureImg);
        getPortraitImage();
        
        //Name & Description
        name = originalName = cardStrings.EXTENDED_DESCRIPTION[this.resourceIndex];
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[this.resourceIndex + 2];
        initializeDescription();
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }
    
    @Override
    public void onChoseThisOption() {
        for (AbstractCard c : this.cardsToModify){
            c.costForTurn += this.costMod;
            if (c.costForTurn < 0){
                c.costForTurn = 0;
            }
            c.isCostModifiedForTurn = true;
        }
    }

    @Override
    public void upp() {
    }

}