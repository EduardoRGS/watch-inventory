package com.watch_inventory.config;

import com.watch_inventory.entity.Watch;
import com.watch_inventory.entity.enums.BoxMaterial;
import com.watch_inventory.entity.enums.GlassType;
import com.watch_inventory.entity.enums.MovementType;
import com.watch_inventory.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class SetupDataInitiation {

    private final WatchRepository watchRepository;

    @Bean
    CommandLineRunner seedWatch() {
        return args -> {
            if (watchRepository.count() > 0) return;
            List<Watch> watches = List.of(
                    Watch.builder()
                            .id(UUID.randomUUID())
                            .mark("Casio")
                            .model("F-91WD")
                            .reference("1234")
                            .movementType(MovementType.QUARTZ)
                            .boxMaterial(BoxMaterial.BRONZE)
                            .glassType(GlassType.ACRILICO)
                            .waterResistanceM(30)
                            .thicknessMm(9)
                            .lugToLugMm(38)
                            .diameterMm(30)
                            .widthMm(10)
                            .priceInCents(1200)
                            .urlImage("123")
                            .createAt(LocalDateTime.now().minusSeconds(50000))
                            .build(),
                    Watch.builder()
                            .id(UUID.randomUUID())
                            .mark("Seiko")
                            .model("Diver 2000m")
                            .reference("0009")
                            .movementType(MovementType.AUTOMATICO)
                            .boxMaterial(BoxMaterial.ACO)
                            .glassType(GlassType.SAFIRA)
                            .waterResistanceM(40)
                            .thicknessMm(10)
                            .lugToLugMm(29)
                            .diameterMm(38)
                            .widthMm(16)
                            .priceInCents(4200)
                            .urlImage("432")
                            .createAt(LocalDateTime.now().minusSeconds(50000))
                            .build()
            );

            watchRepository.saveAll(watches);
        };
    }
}
