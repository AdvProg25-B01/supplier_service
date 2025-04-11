package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierCommandExecutorTest {

    @Test
    void testExecuteCreateCommand() {
        SupplierRepository mockRepo = mock(SupplierRepository.class);

        Supplier supplier = new Supplier(UUID.randomUUID(), "PT ABC", "0812", "Depok", null, null);

        when(mockRepo.save(supplier)).thenReturn(supplier);

        CreateSupplierCommand command = new CreateSupplierCommand(supplier, mockRepo);
        SupplierCommandExecutor executor = new SupplierCommandExecutor();

        Supplier result = executor.execute(command);

        assertNotNull(result);
        assertEquals("PT ABC", result.getName());
        verify(mockRepo).save(supplier);
    }}
