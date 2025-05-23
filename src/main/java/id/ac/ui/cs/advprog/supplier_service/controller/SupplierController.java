package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface SupplierController {
    
    @GetMapping("/suppliers")
    CompletableFuture<ResponseEntity<List<Supplier>>> getAllSuppliers();
    
    @GetMapping("/suppliers/{id}")
    CompletableFuture<ResponseEntity<Supplier>> getSupplierById(@PathVariable UUID id);
    
    @PostMapping("/suppliers")
    CompletableFuture<ResponseEntity<Supplier>> createSupplier(@RequestBody Supplier supplier);
    
    @PutMapping("/suppliers/{id}")
    CompletableFuture<ResponseEntity<Supplier>> updateSupplier(
            @PathVariable UUID id, @RequestBody Supplier supplier);
    
    @DeleteMapping("/suppliers/{id}")
    CompletableFuture<ResponseEntity<Map<String, Object>>> deleteSupplier(@PathVariable UUID id);
    
    @GetMapping("/suppliers/search")
    CompletableFuture<ResponseEntity<List<Supplier>>> searchSuppliersByName(
            @RequestParam("name") String name);
}