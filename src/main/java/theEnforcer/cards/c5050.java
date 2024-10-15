package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.CardModifierManager;
import theEnforcer.enums.CustomTags;
import theEnforcer.modifiers.cardMods.HypeMod;
import theEnforcer.powers.HypePower;
import theEnforcer.util.Wiz;

public class c5050 extends AbstractEnforcerCard {
    public final static String ID = makeID(c5050.class.getSimpleName());

    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS = 3;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public c5050() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.baseBlock = this.block = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choiceCards = new ArrayList<>();
        choiceCards.add(new c50Attack(this.damage, m));
        choiceCards.add(new c50Block(this.block));
        if (this.upgraded){
            for (AbstractCard c: choiceCards){
                c.upgrade();
            }
            Wiz.applyToSelfTop(new HypePower(p));
        }
        addToBot(new ChooseOneAction(choiceCards));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS);
        upgradeBlock(UPGRADE_PLUS);
        tags.add(CustomTags.HYPE_GEN);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}