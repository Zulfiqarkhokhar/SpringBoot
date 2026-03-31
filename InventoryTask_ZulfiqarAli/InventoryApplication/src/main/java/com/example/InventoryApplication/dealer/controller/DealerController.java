package com.example.InventoryApplication.dealer.controller;

import com.example.InventoryApplication.comon.dto.PageResponse;
import com.example.InventoryApplication.dealer.dto.DealerRequest;
import com.example.InventoryApplication.dealer.dto.DealerResponse;
import com.example.InventoryApplication.dealer.service.DealerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService dealerService;

    @PostMapping
    public ResponseEntity<DealerResponse> createDealer(@Valid @RequestBody DealerRequest request) {
        DealerResponse response = dealerService.createDealer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealerResponse> getDealerById(@PathVariable UUID id) {
        DealerResponse response = dealerService.getDealerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<DealerResponse>> getAllDealers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        PageResponse<DealerResponse> response = dealerService.getAllDealers(pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DealerResponse> updateDealer(
            @PathVariable UUID id,
            @Valid @RequestBody DealerRequest request) {
        DealerResponse response = dealerService.updateDealer(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable UUID id) {
        dealerService.deleteDealer(id);
        return ResponseEntity.noContent().build();
    }
}