package org.powerbot.game.api.methods.tab;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Timer;

/**
 * @author ArcaneSanity
 */
public class Prayer {
	public interface PrayerBook {
		public int getId();

		public int getBook();

		public int getRequiredLevel();

		public boolean isActive();

		public boolean isSetQuick();
	}

	public static enum Curses implements PrayerBook {
		PROTECT_ITEM_CURSE(0, 0, 50),
		SAP_WARRIOR(1, 1, 50),
		SAP_RANGER(2, 2, 52),
		SAP_MAGE(3, 3, 54),
		SAP_SPIRIT(4, 4, 56),
		BERSERKER(5, 5, 59),
		DEFLECT_SUMMONING(6, 6, 62),
		DEFLECT_MAGIC(7, 7, 65),
		DEFLECT_MISSILE(8, 8, 68),
		DEFLECT_MELEE(9, 9, 71),
		LEECH_ATTACK(10, 10, 74),
		LEECH_RANGE(11, 11, 76),
		LEECH_MAGIC(12, 12, 78),
		LEECH_DEFENCE(13, 13, 80),
		LEECH_STRENGTH(14, 14, 82),
		LEECH_ENERGY(15, 15, 84),
		LEECH_SPECIAL_ATTACK(16, 16, 86),
		WRATH(17, 17, 89),
		SOUL_SPLIT(18, 18, 92),
		TURMOIL(19, 19, 95);

		private final int id, shift, level;

		Curses(final int id, final int shift, final int level) {
			this.id = id;
			this.shift = shift;
			this.level = level;
		}

		public int getId() {
			return this.id;
		}

		public int getBook() {
			return PRAYER_BOOK_CURSES;
		}

		public int getRequiredLevel() {
			return this.level;
		}

		public boolean isActive() {
			return Settings.get(1582, this.shift, 0x1) == 1;
		}

		public boolean isSetQuick() {
			return Settings.get(1587, this.shift, 0x1) == 1;
		}
	}

	public static enum Normal implements PrayerBook {
		THICK_SKIN(0, 0, 1),
		BURST_OF_STRENGTH(1, 1, 4),
		CLARITY_OF_THOUGHT(2, 2, 7),
		SHARP_EYE(3, 18, 8),
		MYSTIC_WILL(4, 19, 9),
		ROCK_SKIN(5, 3, 10),
		SUPERHUMAN_STRENGTH(6, 4, 13),
		IMPROVED_REFLEXES(7, 5, 16),
		RAPID_RESTORE(8, 6, 19),
		RAPID_HEAL(9, 7, 22),
		PROTECT_ITEM_REGULAR(10, 8, 25),
		HAWK_EYE(11, 20, 26),
		MYSTIC_LORE(12, 21, 27),
		STEEL_SKIN(13, 9, 28),
		ULTIMATE_STRENGTH(14, 10, 31),
		INCREDIBLE_REFLEXES(15, 11, 34),
		PROTECT_FROM_SUMMONING(16, 24, 35),
		PROTECT_FROM_MAGIC(17, 12, 37),
		PROTECT_FROM_MISSILES(18, 13, 40),
		PROTECT_FROM_MELEE(19, 14, 43),
		EAGLE_EYE(20, 22, 44),
		MYSTIC_MIGHT(21, 23, 45),
		RETRIBUTION(22, 15, 46),
		REDEMPTION(23, 16, 49),
		SMITE(24, 17, 52),
		CHIVALRY(25, 25, 60),
		RAPID_RENEWAL(26, 27, 65),
		PIETY(27, 26, 70),
		RIGOUR(28, 29, 74),
		AUGURY(29, 28, 77);

		private final int id, shift, level;

		private Normal(final int id, final int shift, final int level) {
			this.id = id;
			this.shift = shift;
			this.level = level;
		}

		public int getId() {
			return this.id;
		}

		public int getBook() {
			return PRAYER_BOOK_NORMAL;
		}

		public int getRequiredLevel() {
			return this.level;
		}

		public boolean isActive() {
			return Settings.get(1395, this.shift, 0x1) == 1;
		}

		public boolean isSetQuick() {
			return Settings.get(1397, this.shift, 0x1) == 1;
		}
	}

	public static final int WIDGET_PRAYER = 271;
	public static final int WIDGET_PRAYER_ORB = 749;
	public static final int PRAYER_BOOK_CURSES = 0x17;
	public static final int PRAYER_BOOK_NORMAL = 0x16;

	public static final int ICON_PROTECT_FROM_MELEE = 0;
	public static final int ICON_PROTECT_FROM_MISSILES = 1;
	public static final int ICON_PROTECT_FROM_MAGIC = 2;
	public static final int ICON_RETRIBUTION = 3;
	public static final int ICON_REDEMPTION = 4;
	public static final int ICON_SMITE = 5;
	public static final int ICON_PROTECT_FROM_SUMMONING = 7;
	public static final int ICON_PROTECT_FROM_SUMMONING_AND_MELEE = 8;
	public static final int ICON_PROTECT_FROM_SUMMONING_AND_MISSILES = 9;
	public static final int ICON_PROTECT_FROM_SUMMONING_AND_MAGIC = 10;
	public static final int ICON_DEFLECT_MELEE = 12;
	public static final int ICON_DEFLECT_MAGIC = 13;
	public static final int ICON_DEFLECT_MISSILES = 14;
	public static final int ICON_DEFLECT_SUMMONING = 15;
	public static final int ICON_DEFLECT_SUMMONING_AND_MELEE = 16;
	public static final int ICON_DEFLECT_SUMMONING_AND_MISSILES = 17;
	public static final int ICON_DEFLECT_SUMMONING_AND_MAGIC = 18;
	public static final int ICON_WRATH = 19;
	public static final int ICON_SOUL_SPLIT = 20;

	/**
	 * Retrieves the current prayer points.
	 *
	 * @return Current prayer points.
	 */
	public static int getPoints() {
		return Settings.get(2382, 0x3ff);
	}

	/**
	 * Determines whether current prayer book is ancient curses.
	 *
	 * @return <tt>true</tt> if on curses, otherwise <tt>false</tt>.
	 */
	public static boolean isCursesOn() {
		return Settings.get(1584) % 2 != 0;
	}

	/**
	 * Determines whether the quick prayers/curses are on.
	 *
	 * @return <tt>true</tt> if quick prayers/curses are on, otherwise <tt>false</tt>.
	 */
	public static boolean isQuickOn() {
		return Settings.get(1396) == 0x2;
	}

	/**
	 * Retrieves prayers/curses that are currently active.
	 *
	 * @return An array of currently active prayers/curses.
	 */
	public static PrayerBook[] getActive() {
		Set<PrayerBook> active = new LinkedHashSet<PrayerBook>();
		for (PrayerBook p : isCursesOn() ? Curses.values() : Normal.values()) {
			if (p.isActive()) {
				active.add(p);
			}
		}
		return active.toArray(new PrayerBook[active.size()]);
	}

	/**
	 * Retrieves prayers/curses set to quick-use.
	 *
	 * @return An array of currently set prayers/curses to quick-use.
	 */
	public static PrayerBook[] getQuick() {
		Set<PrayerBook> setquick = new LinkedHashSet<PrayerBook>();
		for (PrayerBook p : isCursesOn() ? Curses.values() : Normal.values()) {
			if (p.isSetQuick()) {
				setquick.add(p);
			}
		}
		return setquick.toArray(new PrayerBook[setquick.size()]);
	}

	/**
	 * Turns on or off quick prayers/curses.
	 *
	 * @param activate <tt>true</tt> to turn quick prayers/curses on, <tt>false</tt> to turn quick prayers/curses off.
	 * @return <tt>true</tt> if quick prayers/curses turned on/off, otherwise <tt>false</tt>.
	 */
	public static boolean toggleQuick(final boolean activate) {
		return isQuickOn() == activate || Widgets.get(WIDGET_PRAYER_ORB, 2).interact("Turn");
	}

	/**
	 * Sets given prayers/curses to quick-use.
	 *
	 * @param prayers Prayers/Curses to set to quick use.
	 * @return <tt>true</tt> if prayers/curses successfully set to quick-use, otherwise <tt>false</tt>.
	 */
	public static boolean setQuick(final PrayerBook... prayers) {
		for (PrayerBook p : prayers) {
			if (p.getBook() != (isCursesOn() ? PRAYER_BOOK_CURSES : PRAYER_BOOK_NORMAL)
					|| p.getRequiredLevel() > Skills.getRealLevel(Skills.PRAYER)) {
				return false;
			}
		}
		if (Widgets.get(WIDGET_PRAYER_ORB, 2).interact("Select quick")) {
			final Timer timer = new Timer(1000);
			while (timer.isRunning() && Settings.get(1396) != 0x1) {
				Task.sleep(15);
			}
			Task.sleep(100);
			for (PrayerBook p : getQuick()) {
				if (Arrays.binarySearch(prayers, p) < 0) {
					if (Widgets.get(WIDGET_PRAYER, 42).getChild(p.getId()).interact("Deselect")) {
						final Timer t = new Timer(500);
						while (t.isRunning() && !p.isSetQuick()) {
							Task.sleep(15);
						}
					} else {
						Widgets.get(WIDGET_PRAYER, 43).interact("Confirm");
						return false;
					}
				}
			}
			Arrays.sort(prayers);
			for (PrayerBook p : prayers) {
				if (p.isSetQuick()) {
					continue;
				}
				if (Widgets.get(WIDGET_PRAYER, 42).getChild(p.getId()).interact("Select")) {
					final Timer t = new Timer(500);
					while (t.isRunning() && !p.isSetQuick()) {
						Task.sleep(15);
					}
				} else {
					Widgets.get(WIDGET_PRAYER, 43).interact("Confirm");
					return false;
				}
			}
			return Widgets.get(WIDGET_PRAYER, 43).interact("Confirm");
		}
		return false;
	}

	/**
	 * Activates and deactivates quick prayers/curses in short time resulting in prayers/curses effects and no prayer points loss.
	 *
	 * @return <tt>true</tt> if flash was successful, otherwise <tt>false</tt>.
	 */
	public static boolean flashQuick() {
		final Point point = Widgets.get(WIDGET_PRAYER_ORB, 2).getNextViewportPoint();
		if (Mouse.click(point, true)) {
			Task.sleep(250, 350);
			return Mouse.click(point, true);
		}
		return false;
	}

	/**
	 * Activates or deactivates given prayer.
	 *
	 * @param prayer   Desired prayer/curse.
	 * @param activate <tt>true</tt> to activate, <tt>false</tt> to deactivate.
	 * @return <tt>true</tt> if prayer/curse successfully activated/deactivated, <tt>false</tt> if failed to
	 *         activate/deactivate or player does not meet requirements for given prayer/curse.
	 */
	public static boolean togglePrayer(final PrayerBook prayer, final boolean activate) {
		if (prayer.getBook() != (isCursesOn() ? PRAYER_BOOK_CURSES : PRAYER_BOOK_NORMAL)
				|| prayer.getRequiredLevel() > Skills.getRealLevel(Skills.PRAYER)) {
			return false;
		}
		if (prayer.isActive() == activate) {
			return true;
		}
		if (Tabs.PRAYER.open(false)) {
			return Widgets.get(WIDGET_PRAYER, 8).getChild(prayer.getId()).interact(activate ? "Activate" : "Deactivate");
		}
		return false;
	}

	/**
	 * Activates and deactivates prayer/curse in short time resulting in prayer/curse effect and no prayer points loss.
	 *
	 * @param prayer Desired prayer/curse to flash.
	 * @return <tt>true</tt> if flash was successful, otherwise <tt>false</tt>.
	 */
	public static boolean flashPrayer(final PrayerBook prayer) {
		if (Tabs.PRAYER.open(false)) {
			final Point point = Widgets.get(WIDGET_PRAYER, 8).getChild(prayer.getId()).getNextViewportPoint();
			if (Mouse.click(point, true)) {
				Task.sleep(250, 350);
				return Mouse.click(point, true);
			}
		}
		return false;
	}

	/**
	 * Deactivates all active prayers/curses.
	 *
	 * @return <tt>true</tt> if all prayers/curses deactivated, otherwise <tt>false</tt>.
	 */
	public static boolean deactivateAll() {
		if (getActive().length == 0) {
			return true;
		}
		for (PrayerBook p : getActive()) {
			if (togglePrayer(p, false)) {
				final Timer timer = new Timer(500);
				while (timer.isRunning() && p.isActive()) {
					Task.sleep(15);
				}
			}
		}
		return getActive().length == 0;
	}
}
