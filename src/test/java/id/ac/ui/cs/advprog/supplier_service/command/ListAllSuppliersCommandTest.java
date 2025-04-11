package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ListAllSuppliersCommandTest {

    @Test
    void testExecuteShouldReturnAllSuppliers() {
        SupplierRepository repository = mock(SupplierRepository.class);
        List<Supplier> supplierList = List.of(
                Supplier.builder().name("A").build(),
                Supplier.builder().name("B").build()
        );

        when(repository.findAll()).thenReturn(supplierList);

        ListAllSuppliersCommand command = new ListAllSuppliersCommand(repository);
        List<Supplier> result = command.execute();

        assertEquals(supplierList, result);
        verify(repository).findAll();
    }
}
