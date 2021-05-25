package org.huokan.client.models.offers;

import com.google.auto.value.AutoValue;
import org.huokan.client.models.wow.ArmorType;
import org.huokan.client.models.wow.PrimaryStat;
import org.huokan.client.models.wow.Role;
import org.huokan.client.models.wow.WeaponType;

import java.util.List;

@AutoValue
public abstract class LootFunnelFilter {
    public abstract ArmorType armorType();

    public abstract List<WeaponType> weaponTypes();

    public abstract PrimaryStat primaryStat();

    public abstract Role trinketType();

    public static Builder builder() {
        return new AutoValue_LootFunnelFilter.Builder();
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
        if (armorType() != null) {
            sb.append(armorType().toString().toLowerCase()).append("\n");
        } else {
            sb.append("any");
        }
    }

    private void appendWeaponTypes(StringBuilder sb) {
        if (weaponTypes() != null) {
            var weaponTypeNames = weaponTypes().stream().map(wt -> wt.toString()).toList();
            sb.append(String.join("/", weaponTypeNames));
        } else {
            sb.append("any");
        }
    }

    private void appendPrimaryStat(StringBuilder sb) {
        if (primaryStat() != null) {
            sb.append(primaryStat().toString().toLowerCase());
        } else {
            sb.append("any");
        }
    }

    private void appendTrinketType(StringBuilder sb) {
        if (trinketType() != null) {
            sb.append(trinketType().toString().toLowerCase()).append("\n");
        } else {
            sb.append("any\n");
        }
    }

    public boolean isEmpty() {
        return armorType() == null && weaponTypes() == null && primaryStat() == null && trinketType() == null;
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setArmorType(ArmorType armorType);

        public abstract Builder setWeaponTypes(List<WeaponType> weaponTypes);

        public abstract Builder setPrimaryStat(PrimaryStat primaryStat);

        public abstract Builder setTrinketType(Role trinketType);

        public abstract LootFunnelFilter build();
    }
}
