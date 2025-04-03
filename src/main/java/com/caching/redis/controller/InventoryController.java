package com.caching.redis.controller;

import com.caching.redis.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
/*
 * @author Akmal Ahmed
 * this controller used to test the lock service for updating current inventory object used by another process or deployment
 */
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/update/{itemId}")
    public String updateInventory(@PathVariable Long itemId) {
        return inventoryService.updateInventoryItem(itemId);
    }
}
