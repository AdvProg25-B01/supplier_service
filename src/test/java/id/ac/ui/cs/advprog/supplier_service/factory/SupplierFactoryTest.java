package id.ac.ui.cs.advprog.supplier_service.factory;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SupplierFactoryTest {

    @Test
    void testCreateSupplierShouldReturnValidSupplier() {
        String name = "Bambang";
        String phone = "08123456789";
        String address = "Jakarta";

        Supplier supplier = SupplierFactory.createSupplier(name, phone, address);

        assertNotNull(supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertNotNull(supplier.getCreatedAt());
        assertNotNull(supplier.getUpdatedAt());
    }

    @Test
    void testCreateEmptySupplier() {
        Supplier supplier = SupplierFactory.createEmptySupplier();

        assertNotNull(supplier.getId());
        assertEquals("PlaceholderName", supplier.getName());
        assertEquals("PlaceholderNo", supplier.getPhoneNumber());
        assertEquals("PlaceholderAddress", supplier.getAddress());
        assertNotNull(supplier.getCreatedAt());
        assertNotNull(supplier.getUpdatedAt());
    }

    @Test
    void testCreateSupplierFromRawData() {
        UUID id = UUID.randomUUID();
        String name = "Supplier Test";
        String phone = "08001111222";
        String address = "Bandung";
        Date createdAt = new Date();

        Supplier supplier = SupplierFactory.createFromRawData(id, name, phone, address, createdAt);

        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertEquals(createdAt, supplier.getCreatedAt());
    }
}
