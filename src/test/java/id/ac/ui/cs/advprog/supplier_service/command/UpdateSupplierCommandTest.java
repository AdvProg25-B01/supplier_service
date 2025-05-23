package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class UpdateSupplierCommandTest {

    @Test
    void testExecuteUpdatesExistingSupplier() {
        SupplierRepository repository = mock(SupplierRepository.class);
        
        UUID id = UUID.randomUUID();
        Date createdAt = new Date();
        
        Supplier existingSupplier = Supplier.builder()
                .id(id)
                .name("Original Name")
                .phoneNumber("123456789")
                .address("Original Address")
                .createdAt(createdAt)
                .updatedAt(createdAt)
                .build();
        
        Supplier updatedData = new Supplier();
        updatedData.setId(id);
        updatedData.setName("Updated Name");
        updatedData.setPhoneNumber("987654321");
        updatedData.setAddress("Updated Address");
        
        when(repository.findById(id)).thenReturn(Optional.of(existingSupplier));
        when(repository.save(any(Supplier.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        UpdateSupplierCommand command = new UpdateSupplierCommand(updatedData, repository);
        Supplier result = command.execute();
        
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Updated Name", result.getName());
        assertEquals("987654321", result.getPhoneNumber());
        assertEquals("Updated Address", result.getAddress());
        assertEquals(createdAt, result.getCreatedAt()); // Original createdAt should be preserved
        assertNotNull(result.getUpdatedAt()); // updatedAt should be updated
        
        verify(repository).findById(id);
        verify(repository).save(any(Supplier.class));
    }
    
    @Test
    void testExecuteHandlesMissingSupplier() {
        SupplierRepository repository = mock(SupplierRepository.class);
        UUID id = UUID.randomUUID();
        
        Supplier newSupplier = new Supplier();
        newSupplier.setId(id);
        newSupplier.setName("New Supplier");
        newSupplier.setPhoneNumber("123456789");
        newSupplier.setAddress("New Address");
        
        when(repository.findById(id)).thenReturn(Optional.empty());
        when(repository.save(any(Supplier.class))).thenAnswer(invocation -> {
            Supplier saved = invocation.getArgument(0);
            assertNotNull(saved.getCreatedAt(), "CreatedAt should be set before save");
            assertNotNull(saved.getUpdatedAt(), "UpdatedAt should be set before save");
            return saved;
        });
        
        UpdateSupplierCommand command = new UpdateSupplierCommand(newSupplier, repository);
        Supplier result = command.execute();
        
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("New Supplier", result.getName());
        assertEquals("123456789", result.getPhoneNumber());
        assertEquals("New Address", result.getAddress());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        
        verify(repository).findById(id);
        verify(repository).save(any(Supplier.class));
    }
}