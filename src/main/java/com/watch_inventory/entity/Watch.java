package com.watch_inventory.entity;

import com.watch_inventory.entity.enums.BoxMaterial;
import com.watch_inventory.entity.enums.GlassType;
import com.watch_inventory.entity.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity

@Table(name = "watch", indexes = {
        @Index(name = "IDX_WATCH_MARK", columnList = "mark"),
        @Index(name = "IDX_WATCH_CREATED_AT", columnList = "createdAt"),
        @Index(name = "IDX_WATCH_PRICE", columnList = "priceInCents")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Watch {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, length = 80)
    private String mark;

    @Column(nullable = false, length = 125)
    private String model;

    @Column(nullable = false, length = 125)
    private String reference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovementType movementType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BoxMaterial boxMaterial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private GlassType glassType;

    @Column(nullable = false)
    private int waterResistanceM;

    @Column(nullable = false)
    private int diameterMm;

    @Column(nullable = false)
    private int lugToLugMm;

    @Column(nullable = false)
    private int thicknessMm;

    @Column(nullable = false)
    private int widthMm;

    @Column(nullable = false)
    private long priceInCents;

    @Column(nullable = false, length = 600)
    private String urlImage;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @PrePersist
    void prePersist() {
        if(id == null) id = UUID.randomUUID();
        if (createAt == null) createAt = LocalDateTime.now();
        normalizer();
    }

    @PreUpdate
    void preUpdate() {
        normalizer();
    }

    private void normalizer() {
        if (mark != null) mark = mark.trim();
        if (model != null) model = model.trim();
        if (reference != null) reference = reference.trim();
        if (urlImage != null) urlImage = urlImage.trim();
    }

}
