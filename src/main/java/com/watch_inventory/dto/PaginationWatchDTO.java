package com.watch_inventory.dto;

import java.util.List;

public record PaginationWatchDTO(List<WatchDTO> items,
                                 long total) {
}
