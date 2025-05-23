package id.ac.ui.cs.advprog.supplier_service.service;

import id.ac.ui.cs.advprog.supplier_service.command.*;
import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class AsyncSupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private SupplierCommandExecutor commandExecutor;

    @InjectMocks
    private AsyncSupplierService asyncSupplierService;

    private Supplier testSupplier;
    private UUID testId;
    private List<Supplier> supplierList;
    private Map<String, Object> deleteResponse;

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

        supplierList = Collections.singletonList(testSupplier);

        deleteResponse = new HashMap<>();
        deleteResponse.put("success", true);
        deleteResponse.put("id", testId);
    }

    @Test
    void findAllSuppliersAsync_ShouldReturnSuppliersList() throws ExecutionException, InterruptedException {
        when(commandExecutor.execute(any(ListAllSuppliersCommand.class))).thenReturn(supplierList);

        CompletableFuture<List<Supplier>> future = asyncSupplierService.findAllSuppliersAsync();
        List<Supplier> result = future.get();

        assertEquals(supplierList, result);
        verify(commandExecutor).execute(any(ListAllSuppliersCommand.class));
    }

    @Test
    void findSupplierByIdAsync_ShouldReturnSupplier() throws ExecutionException, InterruptedException {
        when(commandExecutor.execute(any(GetSupplierByIdCommand.class))).thenReturn(testSupplier);

        CompletableFuture<Supplier> future = asyncSupplierService.findSupplierByIdAsync(testId);
        Supplier result = future.get();

        assertEquals(testSupplier, result);
        verify(commandExecutor).execute(any(GetSupplierByIdCommand.class));
    }

    @Test
    void searchSuppliersByNameAsync_ShouldReturnMatchingSuppliers() throws ExecutionException, InterruptedException {
        String searchName = "test";
        when(commandExecutor.execute(any(GetSupplierByNameCommand.class))).thenReturn(supplierList);

        CompletableFuture<List<Supplier>> future = asyncSupplierService.searchSuppliersByNameAsync(searchName);
        List<Supplier> result = future.get();

        assertEquals(supplierList, result);
        verify(commandExecutor).execute(any(GetSupplierByNameCommand.class));
    }

    @Test
    void createSupplierAsync_ShouldCreateAndReturnSupplier() throws ExecutionException, InterruptedException {
        when(commandExecutor.execute(any(CreateSupplierCommand.class))).thenReturn(testSupplier);

        CompletableFuture<Supplier> future = asyncSupplierService.createSupplierAsync(testSupplier);
        Supplier result = future.get();

        assertEquals(testSupplier, result);
        verify(commandExecutor).execute(any(CreateSupplierCommand.class));
    }

    @Test
    void updateSupplierAsync_ShouldUpdateAndReturnSupplier() throws ExecutionException, InterruptedException {
        when(commandExecutor.execute(any(UpdateSupplierCommand.class))).thenReturn(testSupplier);

        CompletableFuture<Supplier> future = asyncSupplierService.updateSupplierAsync(testSupplier);
        Supplier result = future.get();

        assertEquals(testSupplier, result);
        verify(commandExecutor).execute(any(UpdateSupplierCommand.class));
    }

    @Test
    void deleteSupplierAsync_ShouldDeleteAndReturnResponse() throws ExecutionException, InterruptedException {
        when(commandExecutor.execute(any(DeleteSupplierCommand.class))).thenReturn(deleteResponse);

        CompletableFuture<Map<String, Object>> future = asyncSupplierService.deleteSupplierAsync(testId);
        Map<String, Object> result = future.get();

        assertEquals(deleteResponse, result);
        verify(commandExecutor).execute(any(DeleteSupplierCommand.class));
    }
}