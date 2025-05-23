package id.ac.ui.cs.advprog.supplier_service.service;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import id.ac.ui.cs.advprog.supplier_service.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncSupplierService {
    
    private static final Logger logger = LoggerFactory.getLogger(AsyncSupplierService.class);
    
    private final SupplierRepository supplierRepository;
    private final SupplierCommandExecutor commandExecutor;
    
    public AsyncSupplierService(SupplierRepository supplierRepository, SupplierCommandExecutor commandExecutor) {
        this.supplierRepository = supplierRepository;
        this.commandExecutor = commandExecutor;
    }
    
    @Async("taskExecutor")
    public CompletableFuture<List<Supplier>> findAllSuppliersAsync() {
        try {
            ListAllSuppliersCommand command = new ListAllSuppliersCommand(supplierRepository);
            List<Supplier> suppliers = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(suppliers);
        } catch (Exception e) {
            logger.error("Error finding all suppliers: {}", e.getMessage());
            return handleException("Failed to retrieve suppliers", e);
        }
    }
    
    @Async("taskExecutor")
    public CompletableFuture<Supplier> findSupplierByIdAsync(UUID id) {
        try {
            GetSupplierByIdCommand command = new GetSupplierByIdCommand(id, supplierRepository);
            Supplier supplier = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(supplier);
        } catch (Exception e) {
            logger.error("Error finding supplier with ID {}: {}", id, e.getMessage());
            return handleException("Failed to retrieve supplier with ID " + id, e);
        }
    }
    
    @Async("taskExecutor")
    public CompletableFuture<List<Supplier>> searchSuppliersByNameAsync(String name) {
        try {
            GetSupplierByNameCommand command = new GetSupplierByNameCommand(name, supplierRepository);
            List<Supplier> suppliers = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(suppliers);
        } catch (Exception e) {
            logger.error("Error searching suppliers by name '{}': {}", name, e.getMessage());
            return handleException("Failed to search suppliers with name containing " + name, e);
        }
    }
    
    @Async("taskExecutor") 
    public CompletableFuture<Supplier> createSupplierAsync(Supplier supplier) {
        try {
            CreateSupplierCommand command = new CreateSupplierCommand(supplier, supplierRepository);
            Supplier created = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(created);
        } catch (Exception e) {
            logger.error("Error creating supplier: {}", e.getMessage());
            return handleException("Failed to create supplier", e);
        }
    }
    
    @Async("taskExecutor")
    public CompletableFuture<Supplier> updateSupplierAsync(Supplier supplier) {
        try {
            UpdateSupplierCommand command = new UpdateSupplierCommand(supplier, supplierRepository);
            Supplier updated = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(updated);
        } catch (ResponseStatusException e) {
            logger.error("Error updating supplier: {}", e.getMessage());
            CompletableFuture<Supplier> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        } catch (Exception e) {
            logger.error("Error updating supplier with ID {}: {}", 
                supplier.getId(), e.getMessage());
            return handleException("Failed to update supplier with ID " + supplier.getId(), e);
        }
    }
    
    @Async("taskExecutor")
    public CompletableFuture<Map<String, Object>> deleteSupplierAsync(UUID id) {
        try {
            DeleteSupplierCommand command = new DeleteSupplierCommand(id, supplierRepository);
            Map<String, Object> result = commandExecutor.execute(command);
            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            logger.error("Error deleting supplier with ID {}: {}", id, e.getMessage());
            return handleMapException("Failed to delete supplier with ID " + id, e);
        }
    }
    
    private <T> CompletableFuture<T> handleException(String message, Exception e) {
        CompletableFuture<T> future = new CompletableFuture<>();
        
        if (e instanceof ResponseStatusException rse) {
            future.completeExceptionally(rse);
        } else {
            future.completeExceptionally(new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                message
            ));
        }
        
        return future;
    }
    
    private CompletableFuture<Map<String, Object>> handleMapException(String message, Exception e) {
        return handleException(message, e);
    }
}