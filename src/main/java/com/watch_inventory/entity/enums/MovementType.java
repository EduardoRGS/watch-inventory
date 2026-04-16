package com.watch_inventory.entity.enums;

public enum MovementType {
    QUARTZ, AUTOMATICO, MANUAL;

    public static MovementType fromApi(String value) {
        if (value == null || value.isBlank()) return null;

        return switch (value) {
            case "quartz" -> QUARTZ;
            case "automatic" -> AUTOMATICO;
            case "manual" -> MANUAL;
            default -> throw new IllegalArgumentException("Tipo de Movimento Inválido: " + value);
        };
    }

    public String toAPi() {
        return switch (this) {
            case QUARTZ -> "quartz";
            case AUTOMATICO -> "automatic";
            case MANUAL -> "manual";
        };
    }
}
