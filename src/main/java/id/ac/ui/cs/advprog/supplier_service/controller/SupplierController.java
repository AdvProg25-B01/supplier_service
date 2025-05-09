package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface SupplierController {

    @GetMapping("/suppliers")
    ResponseEntity<List<Supplier>> getAllSuppliers();

    @GetMapping("/suppliers/{id}")
    ResponseEntity<Supplier> getSupplierById(@PathVariable UUID id);

    @PostMapping("/suppliers")
    ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier);

    @PutMapping("/suppliers/{id}")
    ResponseEntity<Supplier> updateSupplier(@PathVariable UUID id, @RequestBody Supplier supplier);

    @DeleteMapping("/suppliers/{id}")
    ResponseEntity<Void> deleteSupplier(@PathVariable UUID id);

    @GetMapping("/suppliers/search")
    ResponseEntity<List<Supplier>> searchSuppliersByName(@RequestParam("name") String name);

}