package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetSupplierByNameCommandTest {

    private SupplierRepository supplierRepository;
    private Supplier supplier1, supplier2, supplier3;

    @BeforeEach
    void setUp() {
        supplierRepository = mock(SupplierRepository.class);

        supplier1 = Supplier.builder().name("PT Testing Apalah")
                .address("ADADEDACA")
                .phoneNumber("1243567")
                .build();
        supplier2 = Supplier.builder().name("CV Teknologi Bersama")
                .phoneNumber("187654534")
                .address("Jalan baru")
                .build();
        supplier3 = Supplier.builder().name("PT Testindo f")
                .phoneNumber("5444444444")
                .address("INDONESIA")
                .build();

        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier1, supplier2, supplier3));
    }

    @Test
    void testExecuteShouldReturnSuppliersMatchingNameCaseInsensitive() {
        GetSupplierByNameCommand command = new GetSupplierByNameCommand("test", supplierRepository);

        List<Supplier> result = command.execute();

        assertEquals(2, result.size());
        assertTrue(result.contains(supplier1));
        assertTrue(result.contains(supplier3));
    }

    @Test
    void testExecuteWithNoMatchShouldReturnEmptyList() {
        GetSupplierByNameCommand command = new GetSupplierByNameCommand("Nonexistent", supplierRepository);

        List<Supplier> result = command.execute();

        assertTrue(result.isEmpty());
    }

    @Test
    void testExecuteWithExactNameMatch() {
        GetSupplierByNameCommand command = new GetSupplierByNameCommand("CV Teknologi Bersama", supplierRepository);

        List<Supplier> result = command.execute();

        assertEquals(1, result.size());
        assertEquals(supplier2, result.get(0));
    }
}
