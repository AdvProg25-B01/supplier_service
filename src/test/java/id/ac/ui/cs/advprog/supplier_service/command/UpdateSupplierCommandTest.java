package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateSupplierCommandTest {

    @Test
    void testExecuteUpdatesSupplierSuccessfully() {
        SupplierRepository mockRepo = mock(SupplierRepository.class);
        Supplier supplier = new Supplier(UUID.randomUUID(), "Supplier Lama", "0812", "Bandung", null, null);

        when(mockRepo.save(supplier)).thenReturn(supplier);

        UpdateSupplierCommand command = new UpdateSupplierCommand(supplier, mockRepo);
        Supplier result = command.execute();

        assertEquals("Supplier Lama", result.getName());
        verify(mockRepo).save(supplier);
    }
}
