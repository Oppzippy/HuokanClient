package org.huokan.client.models.wow;

public enum WeaponType {
    POLEARM("Polearm"),
    ONE_HANDED_AXE("One-Handed Axe"),
    ONE_HANDED_SWORD("One-Handed Sword"),
    ONE_HANDED_MACE("One-Handed Mace"),
    TWO_HANDED_AXE("Two-Handed Axe"),
    TWO_HANDED_SWORD("Two-Handed Sword"),
    TWO_HANDED_MACE("Two-Handed Mace"),
    WARGLAIVE("Warglaive"),
    FIST_WEAPON("Fist Weapon"),
    HELD_IN_OFF_HAND("Held In Off-hand"),
    DAGGER("Dagger"),
    STAFF("Staff"),
    WAND("Wand"),
    SHIELD("Shield"),
    BOW("Bow"),
    CROSSBOW("Crossbow"),
    GUN("Gun");

    private String botName;

    private WeaponType(String botName) {
        this.botName = botName;
    }

    public String getBotName() {
        return botName;
    }
}
