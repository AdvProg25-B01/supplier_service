package id.ac.ui.cs.advprog.supplier_service.command;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

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
        Date createdAt = new Date(System.currentTimeMillis() - 100000); // older timestamp
        Date oldUpdateTime = new Date(System.currentTimeMillis() - 50000); // older timestamp
        
        Supplier existingSupplier = Supplier.builder()
                .id(id)
                .name("Original Name")
                .phoneNumber("123456789")
                .address("Original Address")
                .createdAt(createdAt)
                .updatedAt(oldUpdateTime)
                .build();
        
        Supplier updatedData = new Supplier();
        updatedData.setId(id);
        updatedData.setName("Updated Name");
        updatedData.setPhoneNumber("987654321");
        updatedData.setAddress("Updated Address");
        updatedData.setCreatedAt(new Date()); // Client tries to change creation date
        updatedData.setUpdatedAt(new Date()); // Client provides update timestamp
        
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
        assertNotEquals(updatedData.getUpdatedAt(), result.getUpdatedAt()); // Client updatedAt should be ignored
        assertTrue(result.getUpdatedAt().after(oldUpdateTime)); // But updatedAt should be newer than the old one
        
        verify(repository).findById(id);
        verify(repository).save(any(Supplier.class));
    }
    
    @Test
    void testExecuteThrowsExceptionForNonexistentSupplier() {
        SupplierRepository repository = mock(SupplierRepository.class);
        UUID id = UUID.randomUUID();
        
        Supplier nonExistentSupplier = new Supplier();
        nonExistentSupplier.setId(id);
        nonExistentSupplier.setName("New Supplier");
        nonExistentSupplier.setPhoneNumber("123456789");
        nonExistentSupplier.setAddress("New Address");
        
        when(repository.findById(id)).thenReturn(Optional.empty());
        
        UpdateSupplierCommand command = new UpdateSupplierCommand(nonExistentSupplier, repository);
        
        ResponseStatusException exception = assertThrows(
            ResponseStatusException.class,
            command::execute
        );
        
        assertEquals(404, exception.getStatusCode().value());
        assertTrue(exception.getMessage().contains(id.toString()));
        
        verify(repository).findById(id);
        verify(repository, never()).save(any()); 
    }
}