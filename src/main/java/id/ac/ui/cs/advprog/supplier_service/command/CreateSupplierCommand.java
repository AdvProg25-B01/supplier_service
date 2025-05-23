package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CreateSupplierCommand implements SupplierCommand {
    private final Supplier supplier;
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier execute() {
        if (supplier.getId() == null) {
            supplier.setId(UUID.randomUUID());
        }
        
        if (supplier.getCreatedAt() == null) {
            supplier.setCreatedAt(new Date());
        }
        
        if (supplier.getUpdatedAt() == null) {
            Date now = new Date();
            supplier.setUpdatedAt(now);
        }
        
        return supplierRepository.save(supplier);
    }
}