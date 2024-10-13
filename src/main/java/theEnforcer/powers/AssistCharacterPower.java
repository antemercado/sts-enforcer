package theEnforcer.powers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;

import static theEnforcer.EnforcerMod.makePowerPath;

import theEnforcer.EnforcerMod;
import theEnforcer.modifiers.EchoMod;
import theEnforcer.modifiers.EchoedEtherealMod;
import theEnforcer.modifiers.GlowEchoMod;
import theEnforcer.modifiers.HypeMod;
import theEnforcer.util.TexLoader;
import java.util.ArrayList;

public class AssistCharacterPower extends AbstractPower{
    public static final String POWER_ID = EnforcerMod.makeID(AssistCharacterPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TexLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TexLoader.getTexture(makePowerPath("placeholder_power32.png"));
    
    public static final Logger logger = LogManager.getLogger(AssistCharacterPower.class.getName());
    
    public ArrayList<PlayerClass> classArray = new ArrayList<>();
    public ArrayList<Boolean> upgradedArray = new ArrayList<>();
    
    public PlayerClass playerClass;
    public Boolean upgraded;

    public AssistCharacterPower(AbstractCreature owner, int amount, PlayerClass playerClass, Boolean upgraded){
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.playerClass = playerClass;
        this.upgraded = upgraded;
        logger.info("Adding playerClass to array: " + playerClass);
        classArray.add(playerClass);
        upgradedArray.add(upgraded);

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()){
            return;
        }
        flash();
        for (int i = 0; i < this.classArray.size(); i++){
            AbstractCard card = getRandomCharCard(classArray.get(i));

            CardModifierManager.addModifier(card, new ExhaustMod());
            CardModifierManager.addModifier(card, new EchoedEtherealMod());
            CardModifierManager.addModifier(card, new GlowEchoMod());

            if (upgradedArray.get(i)){
                CardModifierManager.addModifier(card, new HypeMod());
            }
            addToBot(new MakeTempCardInHandAction(card));
        }
    }

    private AbstractCard getRandomCharCard(PlayerClass playerClass) {
        ArrayList<AbstractCard> tmp = new ArrayList<>();
        CardCrawlGame.characterManager.getCharacter(playerClass).getCardPool(tmp);
        CardGroup cardPool = new CardGroup(CardGroupType.UNSPECIFIED);
        for (AbstractCard c : tmp){
            if (!c.hasTag(CardTags.HEALING) && c.type != CardType.CURSE
                && c.type != CardType.STATUS && !UnlockTracker.isCardLocked(c.cardID)) {
                cardPool.addToTop(c);
            }
        }
        return cardPool.getRandomCard(true).makeCopy();
    }
}
