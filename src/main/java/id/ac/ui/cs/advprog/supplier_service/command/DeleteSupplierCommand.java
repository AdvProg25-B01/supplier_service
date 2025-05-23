package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Optional;
import id.ac.ui.cs.advprog.supplier_service.model.Supplier;

@RequiredArgsConstructor
@Getter
public class DeleteSupplierCommand implements SupplierCommand {
    private final UUID supplierId;
    private final SupplierRepository supplierRepository;

    @Override
    public Map<String, Object> execute() {
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        
        supplierRepository.deleteById(supplierId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Supplier deleted successfully");
        response.put("id", supplierId);
        
        supplier.ifPresent(s -> {
            response.put("supplierName", s.getName());
            response.put("deletedAt", new java.util.Date());
        });
        
        return response;
    }
}