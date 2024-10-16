package theEnforcer.cards;

import static theEnforcer.EnforcerMod.makeID;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import theEnforcer.modifiers.damageMods.IgnoresBlockMod;

public class ArmorCrush extends AbstractEnforcerCard {
    public final static String ID = makeID(ArmorCrush.class.getSimpleName());

    private static final int COST = 1;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public ArmorCrush() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = 0;
        DamageModifierManager.addModifier(this, new IgnoresBlockMod());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        resetDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        resetDescription();
        this.rawDescription += EXTENDED_DESCRIPTION[0];
    }

    
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = mo.currentBlock;
        if (this.upgraded){
            this.baseDamage = this.damage = mo.currentBlock * 2;
        }
        super.calculateCardDamage(mo);
        resetDescription();
        this.rawDescription += EXTENDED_DESCRIPTION[0];
    }
    
    public void upp() {
        this.rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    private void resetDescription() {
        if (upgraded){
            this.rawDescription = UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = DESCRIPTION;
        }
    }
}