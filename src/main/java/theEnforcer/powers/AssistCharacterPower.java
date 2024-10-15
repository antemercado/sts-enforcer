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
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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
import theEnforcer.modifiers.cardMods.EchoMod;
import theEnforcer.modifiers.cardMods.EchoedEtherealMod;
import theEnforcer.modifiers.cardMods.GlowEchoMod;
import theEnforcer.modifiers.cardMods.HypeMod;
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
    
    public static int IDOffset;

    public ArrayList<PlayerClass> classArray = new ArrayList<>();
    public ArrayList<Boolean> upgradedArray = new ArrayList<>();
    
    public Boolean upgraded;
    private AbstractPlayer assistPlayer;
    public PlayerClass playerClass;

    public AssistCharacterPower(AbstractCreature owner, AbstractPlayer assistPlayer, Boolean upgraded){
        name = NAME;
        ID = POWER_ID + IDOffset;
        IDOffset++;

        this.owner = owner;
        this.assistPlayer = assistPlayer;
        this.playerClass = assistPlayer.chosenClass;
        this.upgraded = upgraded;

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
        AbstractCard card = getRandomCharCard(this.playerClass);

        CardModifierManager.addModifier(card, new ExhaustMod());
        CardModifierManager.addModifier(card, new EchoedEtherealMod());
        CardModifierManager.addModifier(card, new GlowEchoMod());

        if (this.upgraded){
            CardModifierManager.addModifier(card, new HypeMod());
        }
        addToBot(new MakeTempCardInHandAction(card));
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

    public void updateDescription() {
        String classTitle = "#y" + this.assistPlayer.getTitle(this.playerClass);
        classTitle = classTitle.replace(" ", " #y");
        this.description = DESCRIPTIONS[0] + classTitle + DESCRIPTIONS[1];
        if (this.upgraded){
            this.description = this.description + DESCRIPTIONS[2];
        }
    }
}
