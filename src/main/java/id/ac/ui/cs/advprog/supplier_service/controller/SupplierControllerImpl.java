package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.command.*;
import id.ac.ui.cs.advprog.supplier_service.command.*;
import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SupplierControllerImpl implements SupplierController {
    @Override
    public ResponseEntity<List<Supplier>> getAllSuppliers() {return null;}

    @Override
    public ResponseEntity<Supplier> getSupplierById(@PathVariable UUID id) {return null;}

    @Override
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {return null;}

    @Override
    public ResponseEntity<Supplier> updateSupplier(@PathVariable UUID id, @RequestBody Supplier supplier) {return null;}

    @Override
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {return null;}

    @Override
    public ResponseEntity<List<Supplier>> searchSuppliersByName(String name) {return null;}
}
