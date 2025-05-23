package id.ac.ui.cs.advprog.supplier_service.repository;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class SupplierRepositoryIntegrationTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void findByNameContainingIgnoreCase_ShouldReturnMatchingSuppliers() {
        // Create test suppliers
        Supplier supplier1 = Supplier.builder()
                .name("ABC Company")
                .phoneNumber("123456789")
                .address("Jakarta")
                .build();
        Supplier supplier2 = Supplier.builder()
                .name("XYZ Corporation")
                .phoneNumber("987654321")
                .address("Bandung")
                .build();
        Supplier supplier3 = Supplier.builder()
                .name("Another Company ABC")
                .phoneNumber("456123789")
                .address("Surabaya")
                .build();
        
        supplierRepository.saveAll(List.of(supplier1, supplier2, supplier3));
        
        // Search and verify
        List<Supplier> results = supplierRepository.findByNameContainingIgnoreCase("abc");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(s -> s.getName().equals("ABC Company")));
        assertTrue(results.stream().anyMatch(s -> s.getName().equals("Another Company ABC")));
    }
}