package com.caching.redis.services;

import com.caching.redis.services.pub_sub.EventPublisher;
import com.caching.redis.utilities.RedisLockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryService {

    private final RedisLockService lockService;
    private final EventPublisher eventPublisher;

    public String updateInventoryItem(Long itemId) {
        String lockKey = "lock:inventory:" + itemId;

        // Try to acquire the lock (timeout 5 seconds)
        if (!lockService.acquireLock(lockKey, 5)) {
            return "Item is being updated by another request. Try again later.";
        }

        try {
            eventPublisher.publishEvent("update inventory item", "Item Id: " + itemId);
            // Simulate inventory update (Replace with actual DB update)
            log.warn("Updating inventory for item " + itemId);
            Thread.sleep(2000); // Simulating slow update
            log.warn("Inventory updated successfully!");

            return "Inventory updated for item " + itemId;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error updating inventory";
        } finally {
            // Release the lock
            lockService.releaseLock(lockKey);
        }
    }
}

