package com.watch_inventory.controller;

import com.watch_inventory.dto.CreateWatchRequest;
import com.watch_inventory.dto.PaginationWatchDTO;
import com.watch_inventory.dto.UpdateWatchRequest;
import com.watch_inventory.dto.WatchDTO;
import com.watch_inventory.service.WatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/watchs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WatchController {

    private final WatchService service;

    @GetMapping
    public PaginationWatchDTO list(
       @RequestParam(defaultValue = "1") int page,
       @RequestParam(defaultValue = "12")     int perPage,
       @RequestParam(required = false) String search,
       @RequestParam(required = false) String mark,
       @RequestParam(required = false) String movementType,
       @RequestParam(required = false) String boxMaterial,
       @RequestParam(required = false) String glassType,
       @RequestParam(required = false) Integer resistanceMin,
       @RequestParam(required = false) Integer resistanceMax,
       @RequestParam(required = false) Long priceMin,
       @RequestParam(required = false) Long priceMax,
       @RequestParam(required = false) Integer thicknessMin,
       @RequestParam(required = false) Integer thicknessMax,
       @RequestParam(required = false) String order
    ) {
        return service.list(page,
                perPage,
                search,
                mark,
                movementType,
                boxMaterial,
                glassType,
                resistanceMin,
                resistanceMax,
                priceMin,
                priceMax,
                thicknessMin,
                thicknessMax,
                order
                );
    }

    @GetMapping("/{id}")
    public WatchDTO findById(@PathVariable UUID id) {
        return service.searchById(id);
    }

    @PostMapping
    public ResponseEntity<WatchDTO> create (@Valid @RequestBody CreateWatchRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @PutMapping("/{id}")
    public WatchDTO update (@PathVariable UUID id, @Valid @RequestBody UpdateWatchRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove (@PathVariable UUID id) {
         service.remove(id);
         return ResponseEntity.noContent().build();
    }
}
