package ludicrousspeed.simulator.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.OverlayMenu;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.screens.DeathScreen;
import ludicrousspeed.LudicrousSpeedMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenPatches {
    private static final Logger logger = LogManager.getLogger(  ScreenPatches.class.getName());

    @SpirePatch(
            clz = CombatRewardScreen.class,
            paramtypez = {SpriteBatch.class},
            method = "render"
    )
    public static class NoRenderBodyPatch {
        public static SpireReturn Prefix(CombatRewardScreen _instance, SpriteBatch sb) {
            if (LudicrousSpeedMod.plaidMode) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = OverlayMenu.class,
            paramtypez = {},
            method = "showCombatPanels"
    )
    public static class TooMdddanyLinesPatch {
        public static SpireReturn Prefix(OverlayMenu _instance) {
            if (LudicrousSpeedMod.plaidMode) {
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = DeathScreen.class,
            paramtypez = {MonsterGroup.class},
            method = SpirePatch.CONSTRUCTOR
    )
    public static class DisableDeathScreenpatch {
        public static SpireReturn Prefix(DeathScreen _instance, MonsterGroup monsterGroup) {
            if (LudicrousSpeedMod.plaidMode) {
                logger.warn("LudicrousSpeed is preventing the DeathScreen constructor!");
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = DeathScreen.class,
            paramtypez = {},
            method = "reopen"
    )
    public static class DisableDeathReopenScreenpatch {
        public static SpireReturn Prefix(DeathScreen _instance) {
            if (LudicrousSpeedMod.plaidMode) {
                AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.DEATH;
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
