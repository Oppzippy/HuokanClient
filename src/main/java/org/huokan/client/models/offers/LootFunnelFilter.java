package org.huokan.client.models.offers;

import com.google.auto.value.AutoValue;
import org.huokan.client.models.wow.ArmorType;
import org.huokan.client.models.wow.PrimaryStat;
import org.huokan.client.models.wow.Role;
import org.huokan.client.models.wow.WeaponType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@AutoValue
public abstract class LootFunnelFilter {
    public abstract Optional<ArmorType> armorType();

    public abstract Optional<List<WeaponType>> weaponTypes();

    public abstract Optional<PrimaryStat> primaryStat();

    public abstract Optional<Role> trinketType();

    public static Builder builder() {
        return new AutoValue_LootFunnelFilter.Builder();
    }

    public static LootFunnelFilter empty() {
        return builder().build();
    }

    public String toCommandArgs() {
        var sb = new StringBuilder();
        appendArmorType(sb);
        sb.append("\n");
        appendWeaponTypes(sb);
        sb.append("\n");
        appendPrimaryStat(sb);
        sb.append("\n");
        appendTrinketType(sb);
        return sb.toString();
    }

    private void appendArmorType(StringBuilder sb) {
        if (armorType().isPresent()) {
            sb.append(armorType().get().toString().toLowerCase());
        } else {
            sb.append("any");
        }
    }

    private void appendWeaponTypes(StringBuilder sb) {
        if (weaponTypes().isPresent()) {
            var weaponTypeNames = weaponTypes().get().stream().map(wt -> wt.getBotName()).toList();
            sb.append(String.join("/", weaponTypeNames));
        } else {
            sb.append("any");
        }
    }

    private void appendPrimaryStat(StringBuilder sb) {
        if (primaryStat().isPresent()) {
            sb.append(primaryStat().get().toString().toLowerCase());
        } else {
            sb.append("any");
        }
    }

    private void appendTrinketType(StringBuilder sb) {
        if (trinketType().isPresent()) {
            sb.append(trinketType().get().toString().toLowerCase());
        } else {
            sb.append("any");
        }
    }

    public boolean isEmpty() {
        return armorType().isEmpty() && weaponTypes().isEmpty() && primaryStat().isEmpty() && trinketType().isEmpty();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setArmorType(@Nullable ArmorType armorType);
        public abstract Builder setWeaponTypes(@Nullable List<WeaponType> weaponTypes);
        public abstract Builder setPrimaryStat(@Nullable PrimaryStat primaryStat);
        public abstract Builder setTrinketType(@Nullable Role trinketType);

        public abstract LootFunnelFilter build();
    }
}
