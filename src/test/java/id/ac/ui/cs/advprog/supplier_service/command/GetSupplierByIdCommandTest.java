package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GetSupplierByIdCommandTest {

    @Test
    void testExecuteShouldReturnSupplier() {
        SupplierRepository repository = mock(SupplierRepository.class);
        UUID id = UUID.randomUUID();
        Supplier supplier = Supplier.builder()
                .id(id)
                .name("Siti")
                .phoneNumber("089876543210")
                .address("Jl. Alternatif No. 5")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(supplier));

        GetSupplierByIdCommand command = new GetSupplierByIdCommand(id, repository);
        Supplier result = command.execute();

        assertEquals(supplier, result);
        verify(repository).findById(id);
    }
    
    @Test
    void testExecuteShouldReturnNullWhenSupplierNotFound() {
        SupplierRepository repository = mock(SupplierRepository.class);
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        GetSupplierByIdCommand command = new GetSupplierByIdCommand(id, repository);
        Supplier result = command.execute();

        assertNull(result);
        verify(repository).findById(id);
    }
}