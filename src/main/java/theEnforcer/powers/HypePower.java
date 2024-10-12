package theEnforcer.powers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theEnforcer.EnforcerMod.makePowerPath;

import theEnforcer.EnforcerMod;
import theEnforcer.actions.HypePowerCheckAction;
import theEnforcer.enums.CustomTags;
import theEnforcer.util.TexLoader;
import theEnforcer.util.Wiz;

public class HypePower extends AbstractPower{

    public static final String POWER_ID = EnforcerMod.makeID(HypePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TexLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TexLoader.getTexture(makePowerPath("placeholder_power32.png"));
    
    public static final Logger logger = LogManager.getLogger(HypePower.class.getName());

    public boolean gainedThisPlay;

    public HypePower(final AbstractCreature owner){
        this(owner, 1);
    }

    public HypePower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.gainedThisPlay = true;

        type = PowerType.BUFF;
        isTurnBased = true;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void stackPower(int stackAmount){
        this.gainedThisPlay = true;
        super.stackPower(stackAmount);
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        Wiz.atb(new HypePowerCheckAction(this));
        this.gainedThisPlay = false;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
