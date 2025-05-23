package id.ac.ui.cs.advprog.supplier_service.controller;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.service.AsyncSupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class SupplierControllerImplTest {

    @Mock
    private AsyncSupplierService asyncSupplierService;

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
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    @Test
    void getAllSuppliers_ShouldReturnAllSuppliers() throws ExecutionException, InterruptedException {
        List<Supplier> suppliers = Collections.singletonList(testSupplier);
        when(asyncSupplierService.findAllSuppliersAsync())
            .thenReturn(CompletableFuture.completedFuture(suppliers));

        CompletableFuture<ResponseEntity<List<Supplier>>> futureResponse = supplierController.getAllSuppliers();
        ResponseEntity<List<Supplier>> response = futureResponse.get(); // Block to get the result for testing

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suppliers, response.getBody());
        verify(asyncSupplierService).findAllSuppliersAsync();
    }

    @Test
    void getSupplierById_WhenSupplierExists_ShouldReturnSupplier() throws ExecutionException, InterruptedException {
        when(asyncSupplierService.findSupplierByIdAsync(testId))
            .thenReturn(CompletableFuture.completedFuture(testSupplier));

        CompletableFuture<ResponseEntity<Supplier>> futureResponse = supplierController.getSupplierById(testId);
        ResponseEntity<Supplier> response = futureResponse.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());
        verify(asyncSupplierService).findSupplierByIdAsync(testId);
    }

    @Test
    void getSupplierById_WhenSupplierDoesNotExist_ShouldReturnNotFound() throws ExecutionException, InterruptedException {
        when(asyncSupplierService.findSupplierByIdAsync(testId))
            .thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<ResponseEntity<Supplier>> futureResponse = supplierController.getSupplierById(testId);
        ResponseEntity<Supplier> response = futureResponse.get();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(asyncSupplierService).findSupplierByIdAsync(testId);
    }

    @Test
    void createSupplier_ShouldCreateAndReturnSupplier() throws ExecutionException, InterruptedException {
        Supplier supplierToCreate = Supplier.builder()
                .name("New Supplier")
                .phoneNumber("987654321")
                .address("New Address")
                .build();

        when(asyncSupplierService.createSupplierAsync(any(Supplier.class)))
            .thenReturn(CompletableFuture.completedFuture(testSupplier));

        CompletableFuture<ResponseEntity<Supplier>> futureResponse = supplierController.createSupplier(supplierToCreate);
        ResponseEntity<Supplier> response = futureResponse.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());
        verify(asyncSupplierService).createSupplierAsync(any(Supplier.class));
    }

    @Test
    void updateSupplier_ShouldUpdateAndReturnSupplier() throws ExecutionException, InterruptedException {
        Supplier supplierToUpdate = Supplier.builder()
                .name("Updated Supplier")
                .phoneNumber("987654321")
                .address("Updated Address")
                .build();

        when(asyncSupplierService.updateSupplierAsync(any(Supplier.class)))
            .thenReturn(CompletableFuture.completedFuture(testSupplier));

        CompletableFuture<ResponseEntity<Supplier>> futureResponse = 
            supplierController.updateSupplier(testId, supplierToUpdate);
        ResponseEntity<Supplier> response = futureResponse.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testSupplier, response.getBody());
        verify(asyncSupplierService).updateSupplierAsync(any(Supplier.class));
    }

    @Test
    void deleteSupplier_ShouldReturnSuccessResponse() throws ExecutionException, InterruptedException {
        Map<String, Object> deleteResult = new HashMap<>();
        deleteResult.put("success", true);
        deleteResult.put("id", testId);

        when(asyncSupplierService.deleteSupplierAsync(testId))
            .thenReturn(CompletableFuture.completedFuture(deleteResult));

        CompletableFuture<ResponseEntity<Map<String, Object>>> futureResponse = 
            supplierController.deleteSupplier(testId);
        ResponseEntity<Map<String, Object>> response = futureResponse.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deleteResult, response.getBody());
        verify(asyncSupplierService).deleteSupplierAsync(testId);
    }
    
    @Test
    void searchSuppliersByName_ShouldReturnMatchingSuppliers() throws ExecutionException, InterruptedException {
        String searchName = "test";
        List<Supplier> suppliers = Collections.singletonList(testSupplier);
        
        when(asyncSupplierService.searchSuppliersByNameAsync(searchName))
            .thenReturn(CompletableFuture.completedFuture(suppliers));

        CompletableFuture<ResponseEntity<List<Supplier>>> futureResponse = 
            supplierController.searchSuppliersByName(searchName);
        ResponseEntity<List<Supplier>> response = futureResponse.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suppliers, response.getBody());
        verify(asyncSupplierService).searchSuppliersByNameAsync(searchName);
    }
}