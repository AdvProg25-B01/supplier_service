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

    public Supplier save(Supplier supplier) {return null}

    public Supplier findById(UUID id) {return null}

    public List<Supplier> getAllSuppliers() {return null}

    public void deleteById(UUID id) {return null}
}
