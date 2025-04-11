package id.ac.ui.cs.advprog.supplier_service.command;
import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListAllSuppliersCommand implements SupplierCommand {
    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> execute() {
        return supplierRepository.findAll();
    }
}
