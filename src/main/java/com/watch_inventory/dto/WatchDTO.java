package com.watch_inventory.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record WatchDTO(UUID id,
                       String mark,
                       String model,
                       String reference,
                       String movementType,
                       String boxMaterial,
                       String glassType,
                       int waterResistanceM,
                       int diameterMm,
                       int lugToLugMm,
                       int thicknessMm,
                       int widthMm,
                       long priceInCents,
                       String urlImage,
                       String waterResistanceLabel,
                       int collectorScore
                       ) {
}
