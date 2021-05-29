package org.huokan.client.models.offers;

import org.huokan.client.models.wow.ArmorType;
import org.huokan.client.models.wow.PrimaryStat;
import org.huokan.client.models.wow.Role;
import org.huokan.client.models.wow.WeaponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class LootFunnelFilterTests {
    @Test
    public void isEmpty() {
        var filter = LootFunnelFilter.empty();
        Assertions.assertEquals(true, filter.isEmpty());
    }

    @Test
    public void notEmptyWithArmorType() {
        var filter = LootFunnelFilter.builder().setArmorType(ArmorType.MAIL).build();
        Assertions.assertEquals(false, filter.isEmpty());
    }

    @Test
    public void notEmptyWithWeaponTypes() {
        var filter = LootFunnelFilter.builder().setWeaponTypes(Set.of(WeaponType.FIST_WEAPON)).build();
        Assertions.assertEquals(false, filter.isEmpty());
    }

    @Test
    public void notEmptyWithEmptyWeaponTypes() {
        var filter = LootFunnelFilter.builder().setWeaponTypes(Set.of()).build();
        Assertions.assertEquals(false, filter.isEmpty());
    }

    @Test
    public void notEmptyWithPrimaryStat() {
        var filter = LootFunnelFilter.builder().setPrimaryStat(PrimaryStat.AGILITY).build();
        Assertions.assertEquals(false, filter.isEmpty());
    }

    @Test
    public void notEmptyWithTrinketType() {
        var filter = LootFunnelFilter.builder().setTrinketType(Role.TANK).build();
        Assertions.assertEquals(false, filter.isEmpty());
    }
}
