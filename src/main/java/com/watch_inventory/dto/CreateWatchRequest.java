package com.watch_inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateWatchRequest(@NotBlank @Size(max = 80)String mark,
                                 @NotBlank @Size(max = 120)    String model,
                                 @NotBlank @Size(max = 80)   String reference,
                                 @NotBlank  String movementType,
                                 @NotBlank String boxMaterial,
                                 @NotBlank String glassType,
                                 @Min(0)  int waterResistanceM,
                                 @Min(20)  int diameterMm,
                                 @Min(20)  int lugToLugMm,
                                 @Min(5)  int thicknessMm,
                                 @Min(10)  int widthMm,
                                 @Min(1) long priceInCents,
                                 @NotNull @Size(max = 600) String urlImage) {
}
