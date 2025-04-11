package id.ac.ui.cs.advprog.supplier_service.repository;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SupplierRepositoryTest {

    private SupplierRepository supplierRepository;
    private Supplier supplier;

    @BeforeEach
    void setUp() {
        supplierRepository = new SupplierRepository();

        supplier = Supplier.builder()
                .id(UUID.randomUUID())
                .name("PT ABC")
                .phoneNumber("081234567890")
                .address("Jl. Jalan No. 1")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    @Test
    void testSaveNewSupplier() {
        Supplier saved = supplierRepository.save(supplier);
        assertNotNull(saved);
        assertEquals(supplier.getName(), saved.getName());
        assertEquals(1, supplierRepository.findAll().size());
    }

    @Test
    void testUpdateExistingSupplier() {
        supplierRepository.save(supplier);
        Supplier updated = Supplier.builder()
                .id(supplier.getId())
                .name("PT ABC Baru")
                .phoneNumber("081234567890")
                .address("Jl. Jalan No. 2")
                .createdAt(supplier.getCreatedAt())
                .updatedAt(new Date())
                .build();

        supplierRepository.save(updated);

        Supplier found = supplierRepository.findById(supplier.getId());
        assertEquals("PT ABC Baru", found.getName());
        assertEquals("Jl. Jalan No. 2", found.getAddress());
        assertEquals(1, supplierRepository.findAll().size());
    }

    @Test
    void testFindById() {
        supplierRepository.save(supplier);
        Supplier found = supplierRepository.findById(supplier.getId());
        assertNotNull(found);
        assertEquals(supplier.getId(), found.getId());
    }

    @Test
    void testFindByIdNotFound() {
        Supplier found = supplierRepository.findById(UUID.randomUUID());
        assertNull(found);
    }

    @Test
    void testDeleteById() {
        supplierRepository.save(supplier);
        supplierRepository.deleteById(supplier.getId());
        assertNull(supplierRepository.findById(supplier.getId()));
        assertTrue(supplierRepository.findAll().isEmpty());
    }

    @Test
    void testfindAll() {
        Supplier another = Supplier.builder()
                .id(UUID.randomUUID())
                .name("CV Bersama")
                .phoneNumber("089876543210")
                .address("Jl. Alternatif No. 5")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        supplierRepository.save(supplier);
        supplierRepository.save(another);

        List<Supplier> all = supplierRepository.findAll();
        assertEquals(2, all.size());
    }
}
