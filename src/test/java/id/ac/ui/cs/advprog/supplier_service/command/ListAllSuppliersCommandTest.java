package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ListAllSuppliersCommandTest {

    @Test
    void testExecuteShouldReturnAllSuppliers() {
        SupplierRepository repository = mock(SupplierRepository.class);
        List<Supplier> supplierList = List.of(
                Supplier.builder().name("A")
                        .id(UUID.randomUUID())
                        .phoneNumber("089876543210")
                        .address("Jl. Alternatif No. 5")
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build(),
                Supplier.builder().name("B")
                        .id(UUID.randomUUID())
                        .phoneNumber("46464646")
                        .address("Jl. Cadangan No. 51")
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build()
        );

        when(repository.findAll()).thenReturn(supplierList);

        ListAllSuppliersCommand command = new ListAllSuppliersCommand(repository);
        List<Supplier> result = command.execute();

        assertEquals(supplierList, result);
        verify(repository).findAll();
    }
}
