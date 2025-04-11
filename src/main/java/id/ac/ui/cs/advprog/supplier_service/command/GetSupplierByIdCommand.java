package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetSupplierByIdCommand implements SupplierCommand {
    private final UUID supplierId;
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier execute() {
        return supplierRepository.findById(supplierId);
    }
}

