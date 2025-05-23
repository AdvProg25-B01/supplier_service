package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.service.AsyncSupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController {

    private final AsyncSupplierService asyncSupplierService;

    @Override
    @GetMapping("/suppliers")
    public CompletableFuture<ResponseEntity<List<Supplier>>> getAllSuppliers() {
        return asyncSupplierService.findAllSuppliersAsync()
                .thenApply(ResponseEntity::ok);
    }

    @Override
    @GetMapping("/suppliers/{id}")
    public CompletableFuture<ResponseEntity<Supplier>> getSupplierById(@PathVariable UUID id) {
        return asyncSupplierService.findSupplierByIdAsync(id)
                .thenApply(supplier -> supplier != null ? 
                        ResponseEntity.ok(supplier) : 
                        ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping("/suppliers")
    public CompletableFuture<ResponseEntity<Supplier>> createSupplier(@RequestBody Supplier supplier) {
        // Ensure ID, createdAt and updatedAt are set
        if (supplier.getId() == null) {
            supplier.setId(UUID.randomUUID());
        }
        
        if (supplier.getCreatedAt() == null) {
            Date now = new Date();
            supplier.setCreatedAt(now);
        }
        
        if (supplier.getUpdatedAt() == null) {
            Date now = new Date();
            supplier.setUpdatedAt(now);
        }
        
        return asyncSupplierService.createSupplierAsync(supplier)
                .thenApply(ResponseEntity::ok);
    }

    @Override
    @PutMapping("/suppliers/{id}")
    public CompletableFuture<ResponseEntity<Supplier>> updateSupplier(
            @PathVariable UUID id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        return asyncSupplierService.updateSupplierAsync(supplier)
                .thenApply(ResponseEntity::ok);
    }

    @Override
    @DeleteMapping("/suppliers/{id}")
    public CompletableFuture<ResponseEntity<Map<String, Object>>> deleteSupplier(@PathVariable UUID id) {
        return asyncSupplierService.deleteSupplierAsync(id)
                .thenApply(ResponseEntity::ok);
    }
    
    @Override
    @GetMapping("/suppliers/search")
    public CompletableFuture<ResponseEntity<List<Supplier>>> searchSuppliersByName(
            @RequestParam("name") String name) {
        return asyncSupplierService.searchSuppliersByNameAsync(name)
                .thenApply(ResponseEntity::ok);
    }
}