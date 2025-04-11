package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteSupplierCommandTest {

    @Test
    void testExecuteDeletesSupplierSuccessfully() {
        SupplierRepository mockRepo = mock(SupplierRepository.class);
        UUID supplierId = UUID.randomUUID();

        DeleteSupplierCommand command = new DeleteSupplierCommand(supplierId, mockRepo);
        command.execute();

        verify(mockRepo, times(1)).deleteById(supplierId);
    }
}
