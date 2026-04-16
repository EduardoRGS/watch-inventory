package com.watch_inventory.entity.enums;

public enum BoxMaterial {
    ACO, TITANIO, RESINA, BRONZE, CERAMICA;

    public static BoxMaterial fromApi(String value) {
        if (value == null || value.isBlank()) return null;

        return switch (value) {
            case "steel" -> ACO;
            case "titanium" -> TITANIO;
            case "resin" -> RESINA;
            case "bronze" -> BRONZE;
            case "ceramic" -> CERAMICA;
            default -> throw new IllegalArgumentException("Material da Caixa Inválido: " + value);
        };
    }

    public String toAPi() {
        return switch (this) {
            case ACO -> "steel";
            case TITANIO -> "titanium";
            case RESINA -> "resin";
            case BRONZE -> "bronze";
            case CERAMICA -> "ceramic";
        };
    };
}
