package id.ac.ui.cs.advprog.supplier_service.repository;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class SupplierRepository {
    // Untuk sementara menggunakan arraylist dan method disesuaikan dengan arraylist
    private final List<Supplier> supplierList = new ArrayList<>();

    public Supplier save(Supplier supplier) {
        int i = 0;
        for (Supplier savedSupplier : supplierList) {
            if (savedSupplier.getId().equals(supplier.getId())) {
                supplierList.remove(i);
                supplierList.add(i, supplier);
                return supplier;
            }
            i++;
        }
        supplierList.add(supplier);
        return supplier;
    }

    public Supplier findById(UUID id) {
        for (Supplier savedSupplier : supplierList) {
            if (savedSupplier.getId().equals(id)) {
                return savedSupplier;
            }
        }
        return null;
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(supplierList);
    }

    public void deleteById(UUID id) {
        supplierList.removeIf(supplier -> supplier.getId().equals(id));
    }
}
