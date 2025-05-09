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

    private final SupplierRepository supplierRepository;
    private final SupplierCommandExecutor commandExecutor;

    @Override
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        ListAllSuppliersCommand command = new ListAllSuppliersCommand(supplierRepository);
        List<Supplier> suppliers = commandExecutor.execute(command);
        return ResponseEntity.ok(suppliers);
    }

    @Override
    public ResponseEntity<Supplier> getSupplierById(@PathVariable UUID id) {
        GetSupplierByIdCommand command = new GetSupplierByIdCommand(id, supplierRepository);
        Supplier supplier = commandExecutor.execute(command);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        CreateSupplierCommand command = new CreateSupplierCommand(supplier, supplierRepository);
        Supplier created = commandExecutor.execute(command);
        return ResponseEntity.ok(created);
    }

    @Override
    public ResponseEntity<Supplier> updateSupplier(@PathVariable UUID id, @RequestBody Supplier supplier) {
        supplier.setId(id);
        UpdateSupplierCommand command = new UpdateSupplierCommand(supplier, supplierRepository);
        Supplier updated = commandExecutor.execute(command);
        return ResponseEntity.ok(updated);
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
        DeleteSupplierCommand command = new DeleteSupplierCommand(id, supplierRepository);
        commandExecutor.execute(command);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Supplier>> searchSuppliersByName(String name) {
        List<Supplier> results = commandExecutor.execute(
                new GetSupplierByNameCommand(name, supplierRepository)
        );
        return ResponseEntity.ok(results);
    }
}
