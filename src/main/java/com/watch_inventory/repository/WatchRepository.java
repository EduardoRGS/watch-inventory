package com.watch_inventory.repository;

import com.watch_inventory.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatchRepository extends JpaRepository<Watch, UUID>, JpaSpecificationExecutor<Watch> {
}
