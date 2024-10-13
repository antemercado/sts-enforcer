package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;
import static theEnforcer.EnforcerMod.modID;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.actions.ApplyAssistCharPower;
import theEnforcer.powers.AssistCharacterPower;
import theEnforcer.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;

public class AssistCharacter extends AbstractEnforcerCard {
    public final static String ID = makeID(AssistCharacter.class.getSimpleName());

    private static final int COST = 2;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;

    public AssistCharacter() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPlayer> chars  =new ArrayList<>();
        for (AbstractPlayer players: CardCrawlGame.characterManager.getAllCharacters()){
            if (players.getCardColor() != p.getCardColor()){
                chars.add(players);
            }
        }
        
        ArrayList<AbstractCard> choiceCards = new ArrayList<>();
        int charSize = chars.size();
        for (int i = 0; (i < 3 && i < charSize); i++){
            Collections.shuffle(chars, AbstractDungeon.cardRandomRng.random);

            AbstractPlayer choicePlayer = chars.get(0);
            chars.remove(0);

            AssistCharHelperCard tmpCard = new AssistCharHelperCard(choicePlayer, getCardImage(choicePlayer));
            choiceCards.add(tmpCard);
        }

        if (this.upgraded){
            for (AbstractCard c: choiceCards){
                c.upgrade();
            }
        }

        addToBot(new ChooseOneAction(choiceCards));
    }

    private String getCardImage(AbstractPlayer choicePlayer) {
        // TODO Auto-generated method stub
        return getCardTextureString(AssistCharHelperCard.ID.replace(modID + ":", ""), TYPE);
    }

    public void upp() {
    }
}