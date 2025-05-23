package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class UpdateSupplierCommand implements SupplierCommand {
    private final Supplier supplier;
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier execute() {
        Optional<Supplier> existingSupplierOpt = supplierRepository.findById(supplier.getId());
        
        if (existingSupplierOpt.isPresent()) {
            Supplier existingSupplier = existingSupplierOpt.get();
            
            existingSupplier.setName(supplier.getName());
            existingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            existingSupplier.setAddress(supplier.getAddress());
            
            existingSupplier.setUpdatedAt(new Date());
            
            return supplierRepository.save(existingSupplier);
        } else {
            if (supplier.getCreatedAt() == null) {
                supplier.setCreatedAt(new Date());
            }
            if (supplier.getUpdatedAt() == null) {
                supplier.setUpdatedAt(new Date());
            }
            return supplierRepository.save(supplier);
        }
    }
}