package com.watch_inventory.entity.enums;

public enum GlassType {
    MINERAL, SAFIRA, ACRILICO;


    public static GlassType fromApi(String value) {
        if (value == null || value.isBlank()) return null;

        return switch (value) {
            case "mineral" -> MINERAL;
            case "sapphire" -> SAFIRA;
            case "acrylic" -> ACRILICO;
            default -> throw new IllegalArgumentException("Tipo de Vidro Inválido: " + value);
        };
    }

    public String toAPi() {
        return switch (this) {
            case MINERAL -> "mineral";
            case SAFIRA -> "sapphire";
            case ACRILICO -> "acrylic";
        };
    }
}
