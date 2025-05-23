package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateSupplierCommandTest {

    @Test
    void testExecuteCreatesSupplierSuccessfully() {
        SupplierRepository mockRepo = mock(SupplierRepository.class);
        Supplier supplier = new Supplier("PT Maju Jaya", "08123456789", "Jakarta");

        when(mockRepo.save(supplier)).thenReturn(supplier);

        CreateSupplierCommand command = new CreateSupplierCommand(supplier, mockRepo);
        Supplier result = command.execute();

        assertEquals("PT Maju Jaya", result.getName());
        verify(mockRepo, times(1)).save(supplier);
    }
}
