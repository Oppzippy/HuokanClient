package org.huokan.client.models.wow;

public enum Dungeon {
    DE_OTHER_SIDE("DoS"), HALLS_OF_ATONEMENT("HoA"), MISTS_OF_TIRNA_SCITHE("MoTS"), THE_NECROTIC_WAKE("NW"),
    PLAGUEFALL("PF"), SANGUINE_DEPTHS("SD"), SPIRES_OF_ASCENSION("SoA"), THEATER_OF_PAIN("ToP");

    private String shortName;

    private Dungeon(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
