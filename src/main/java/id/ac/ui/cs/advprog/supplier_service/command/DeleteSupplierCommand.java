package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteSupplierCommand implements SupplierCommand {
    private final UUID supplierId;
    private final SupplierRepository supplierRepository;

    @Override
    public Object execute() {
        supplierRepository.deleteById(supplierId);
        return null;
    }
}
