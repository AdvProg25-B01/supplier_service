package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class GetSupplierByNameCommand implements SupplierCommand {
    private final String name;
    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> execute() {
        return supplierRepository.findByNameContainingIgnoreCase(name);
    }
}