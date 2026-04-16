package com.watch_inventory.mapper;

import com.watch_inventory.dto.WatchDTO;
import com.watch_inventory.entity.Watch;
import com.watch_inventory.entity.enums.BoxMaterial;
import com.watch_inventory.entity.enums.GlassType;
import com.watch_inventory.entity.enums.MovementType;
import org.springframework.stereotype.Component;

@Component
public class WatchMapper {

    public WatchDTO toDTO(Watch w) {
        return WatchDTO.builder()
                .id(w.getId())
                .mark(w.getMark())
                .model(w.getModel())
                .reference(w.getReference())
                .movementType(w.getMovementType().toAPi())
                .boxMaterial(w.getBoxMaterial().toAPi())
                .glassType(w.getGlassType().toAPi())
                .waterResistanceM(w.getWaterResistanceM())
                .diameterMm(w.getDiameterMm())
                .lugToLugMm(w.getLugToLugMm())
                .thicknessMm(w.getThicknessMm())
                .widthMm(w.getWidthMm())
                .priceInCents(w.getPriceInCents())
                .urlImage(w.getUrlImage())
                .waterResistanceLabel(waterResistanceLabel(w.getWaterResistanceM()))
                .collectorScore(collectorScore(w))
                .build();
    }

    private String waterResistanceLabel (int resistanceM) {
        if (resistanceM < 50) return "SPLASH";
        if (resistanceM < 100) return "DAILY_USE";
        if (resistanceM < 200) return "SWIMMING";
        return "DIVING";
    }

    private int collectorScore (Watch w) {
        int points = 0;

        if (w.getGlassType() == GlassType.SAFIRA) points +=25;
        if (w.getMovementType() == MovementType.AUTOMATICO) points +=25;
        if (w.getBoxMaterial() == BoxMaterial.CERAMICA) points +=12;
        if (w.getBoxMaterial() == BoxMaterial.TITANIO) points +=12;


        if (w.getWaterResistanceM() >= 100) points +=15;
        if(w.getWaterResistanceM() >= 200) points +=10;

        if (w.getDiameterMm() >= 38 && w.getDiameterMm() <= 42) points += 13;

        return points;


    }
}
