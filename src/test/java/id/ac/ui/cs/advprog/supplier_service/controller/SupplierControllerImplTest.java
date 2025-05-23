package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.command.*;
import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class SupplierControllerImplTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierCommandExecutor commandExecutor;

    @InjectMocks
    private SupplierControllerImpl supplierController;

    private Supplier testSupplier;
    private UUID testId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testId = UUID.randomUUID();
        testSupplier = Supplier.builder()
                .id(testId)
                .name("Test Supplier")
                .phoneNumber("123456789")
                .address("Test Address")
                .build();
    }

    @Test
    void getAllSuppliers_ShouldReturnAllSuppliers() {
        // Arrange
        List<Supplier> suppliers = Arrays.asList(testSupplier);
        when(commandExecutor.execute(any(ListAllSuppliersCommand.class))).thenReturn(suppliers);

        // Act
        ResponseEntity<List<Supplier>> response = supplierController.getAllSuppliers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suppliers, response.getBody());
        verify(commandExecutor).execute(any(ListAllSuppliersCommand.class));
    }

    @Test
    void getSupplierById_WhenSupplierExists_ShouldReturnSupplier() {
        when(commandExecutor.execute(any(GetSupplierByIdCommand.class))).thenReturn(testSupplier);

        ResponseEntity<Supplier> response = supplierController.getSupplierById(testId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());

        ArgumentCaptor<GetSupplierByIdCommand> commandCaptor = ArgumentCaptor.forClass(GetSupplierByIdCommand.class);
        verify(commandExecutor).execute(commandCaptor.capture());
        assertEquals(testId, commandCaptor.getValue().getSupplierId());
    }

    @Test
    void getSupplierById_WhenSupplierDoesNotExist_ShouldReturnNotFound() {
        when(commandExecutor.execute(any(GetSupplierByIdCommand.class))).thenReturn(null);

        ResponseEntity<Supplier> response = supplierController.getSupplierById(testId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createSupplier_ShouldCreateAndReturnSupplier() {
        when(commandExecutor.execute(any(CreateSupplierCommand.class))).thenReturn(testSupplier);

        ResponseEntity<Supplier> response = supplierController.createSupplier(testSupplier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());

        ArgumentCaptor<CreateSupplierCommand> commandCaptor = ArgumentCaptor.forClass(CreateSupplierCommand.class);
        verify(commandExecutor).execute(commandCaptor.capture());
        assertEquals(testSupplier, commandCaptor.getValue().getSupplier());
    }

    @Test
    void updateSupplier_ShouldUpdateAndReturnSupplier() {
        Supplier updatedSupplier = Supplier.builder()
                .name("Updated Name")
                .phoneNumber("987654321")
                .address("Updated Address")
                .build();

        when(commandExecutor.execute(any(UpdateSupplierCommand.class))).thenReturn(testSupplier);

        ResponseEntity<Supplier> response = supplierController.updateSupplier(testId, updatedSupplier);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());

        ArgumentCaptor<UpdateSupplierCommand> commandCaptor = ArgumentCaptor.forClass(UpdateSupplierCommand.class);
        verify(commandExecutor).execute(commandCaptor.capture());
        assertEquals(testId, commandCaptor.getValue().getSupplier().getId());
    }

    @Test
    void deleteSupplier_ShouldReturnNoContent() {
        doReturn(null).when(commandExecutor).execute(any(DeleteSupplierCommand.class));

        ResponseEntity<Void> response = supplierController.deleteSupplier(testId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ArgumentCaptor<DeleteSupplierCommand> commandCaptor = ArgumentCaptor.forClass(DeleteSupplierCommand.class);
        verify(commandExecutor).execute(commandCaptor.capture());
        assertEquals(testId, commandCaptor.getValue().getSupplierId());
    }

    @Test
    void searchSuppliersByName_ShouldReturnMatchingSuppliers() {
        List<Supplier> suppliers = Arrays.asList(testSupplier);
        when(commandExecutor.execute(any(GetSupplierByNameCommand.class))).thenReturn(suppliers);

        ResponseEntity<List<Supplier>> response = supplierController.searchSuppliersByName("Test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suppliers, response.getBody());

        ArgumentCaptor<GetSupplierByNameCommand> commandCaptor = ArgumentCaptor.forClass(GetSupplierByNameCommand.class);
        verify(commandExecutor).execute(commandCaptor.capture());
        assertEquals("Test", commandCaptor.getValue().getName());
    }
}