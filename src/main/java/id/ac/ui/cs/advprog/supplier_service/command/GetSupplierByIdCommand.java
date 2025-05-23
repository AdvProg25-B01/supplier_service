package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class GetSupplierByIdCommand implements SupplierCommand {
    private final UUID supplierId;
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier execute() {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        return supplierOptional.orElse(null);
    }
}