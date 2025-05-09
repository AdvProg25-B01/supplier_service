package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class GetSupplierByNameCommand implements SupplierCommand {
    private final String name;
    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> execute() {
        List<Supplier> allSuppliers = supplierRepository.findAll();
        List<Supplier> result = new ArrayList<>();

        for (Supplier supplier : allSuppliers) {
            if (supplier.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(supplier);
            }
        }

        return result;
    }
}