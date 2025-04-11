package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateSupplierCommand implements SupplierCommand {
    private final Supplier supplier;
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier execute() {
        return supplierRepository.save(supplier);
    }
}

