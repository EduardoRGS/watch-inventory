package com.watch_inventory.service;

import com.watch_inventory.dto.CreateWatchRequest;
import com.watch_inventory.dto.PaginationWatchDTO;
import com.watch_inventory.dto.UpdateWatchRequest;
import com.watch_inventory.dto.WatchDTO;
import com.watch_inventory.entity.Watch;
import com.watch_inventory.entity.enums.BoxMaterial;
import com.watch_inventory.entity.enums.GlassType;
import com.watch_inventory.entity.enums.MovementType;
import com.watch_inventory.exception.NotFoundException;
import com.watch_inventory.mapper.WatchMapper;
import com.watch_inventory.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.watch_inventory.service.WatchSpecs.*;

@Service
@RequiredArgsConstructor
public class WatchService {

    private final WatchRepository watchRepository;
    private final WatchMapper watchMapper;

    public PaginationWatchDTO list (
            int page,
            int perPage,
            String search,
            String mark,
            String movementType,
            String boxMaterial,
            String glassType,
            Integer resistanceMin,
            Integer resistanceMax,
            Long priceMin,
            Long priceMax,
            Integer thicknessMin,
            Integer thicknessMax,
            String order
    ) {
        int pageSafe = Math.max(1, page);
        int perPageSafe = Math.min(60, Math.max(1, perPage));

        MovementType movement = MovementType.fromApi(movementType);
        BoxMaterial box = BoxMaterial.fromApi(boxMaterial);
        GlassType glass = GlassType.fromApi(glassType);

        OrderWatch orderWatch = OrderWatch.fromApi(order);

        Sort sort = switch (orderWatch) {
            case MAIS_RECENTES -> Sort.by(Sort.Direction.DESC, "createAt");
            case PRECO_CRESC -> Sort.by(Sort.Direction.ASC, "priceInCents");
            case PRECO_DESC -> Sort.by(Sort.Direction.DESC, "priceInCents");
            case DIAMETRO_CRESC -> Sort.by(Sort.Direction.ASC, "diameterMm");
            case RESISTENCIA_DESC -> Sort.by(Sort.Direction.DESC, "waterResistanceM");
        };

        Pageable pageable = PageRequest.of(pageSafe - 1, perPageSafe, sort);

        Specification<Watch> spec = Specification.where(search(search))
                .and(markEqual(mark))
                .and(movementTypeEqual(movement))
                .and(boxMaterialEqual(box))
                .and(glassTypeEqual(glass))
                .and(waterResistanceBetween(resistanceMin, resistanceMax))
                .and(priceBetween(priceMin, priceMax))
                .and(thicknessBetween(thicknessMin, thicknessMax));

        Page<Watch> result = watchRepository.findAll(spec, pageable);

        return new PaginationWatchDTO(
                result.getContent().stream().map(watchMapper::toDTO).toList(),
                result.getTotalElements()
        );
    }

    public WatchDTO searchById (UUID id) {
        Watch w = watchRepository.findById(id).orElseThrow(() -> new NotFoundException("Relogio não encontrado " + id));
        return watchMapper.toDTO(w);
    }

    public WatchDTO create(CreateWatchRequest req) {
        Watch w = Watch.builder()
                .id(UUID.randomUUID())
                .mark(req.mark())
                .model(req.model())
                .reference(req.reference())
                .movementType(MovementType.fromApi(req.movementType()))
                .boxMaterial(BoxMaterial.fromApi(req.boxMaterial()))
                .glassType(GlassType.fromApi(req.glassType()))
                .waterResistanceM(req.waterResistanceM())
                .thicknessMm(req.thicknessMm())
                .lugToLugMm(req.lugToLugMm())
                .widthMm(req.widthMm())
                .priceInCents(req.priceInCents())
                .urlImage(req.urlImage())
                .createAt(LocalDateTime.now())
                .build();
        return watchMapper.toDTO(watchRepository.save(w));
    }

    public WatchDTO update(UUID id,UpdateWatchRequest req) {
        Watch w = watchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Relogio não encontrado " + id));
        w.setMark(req.mark());
        w.setModel(req.model());
        w.setReference(req.reference());
        w.setMovementType(MovementType.fromApi(req.movementType()));
        w.setBoxMaterial(BoxMaterial.fromApi(req.boxMaterial()));
        w.setGlassType(GlassType.fromApi(req.glassType()));
        w.setWaterResistanceM(req.waterResistanceM());
        w.setThicknessMm(req.thicknessMm());
        w.setLugToLugMm(req.lugToLugMm());
        w.setWidthMm(req.widthMm());
        w.setPriceInCents(req.priceInCents());
        w.setUrlImage(req.urlImage());
        return watchMapper.toDTO(watchRepository.save(w));
    }

    public void remove (UUID id) {
        if (!watchRepository.existsById(id))
            throw  new NotFoundException("Relogio não encontrado " + id);
        watchRepository.deleteById(id);
    }
}
