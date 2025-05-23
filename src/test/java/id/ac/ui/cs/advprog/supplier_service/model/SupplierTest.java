package id.ac.ui.cs.advprog.supplier_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

    private UUID id;
    private String name;
    private String phone;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();
        name = "PT Sumber Jaya";
        phone = "081234567890";
        address = "Jalan Mawar No. 10";
        createdAt = new Date();
        updatedAt = new Date();
    }

    @Test
    void testCreateSupplierWithConstructor() {
        Supplier supplier = new Supplier(name, phone, address);
        
        assertNotNull(supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertNotNull(supplier.getCreatedAt());
        assertNotNull(supplier.getUpdatedAt());
    }

    @Test
    void testCreateSupplierWithBuilder() {
        Supplier supplier = Supplier.builder()
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertNotNull(supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertNotNull(supplier.getCreatedAt());
        assertNotNull(supplier.getUpdatedAt());
    }
    
    @Test
    void testCreateSupplierWithCustomId() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
    }

    @Test
    void testSetName() {
        Supplier supplier = new Supplier(name, phone, address);
        String newName = "PT Baru Jaya";
        
        supplier.setName(newName);
        assertEquals(newName, supplier.getName());
    }

    @Test
    void testSetPhoneNumber() {
        Supplier supplier = new Supplier(name, phone, address);
        String newPhone = "087654321098";
        
        supplier.setPhoneNumber(newPhone);
        assertEquals(newPhone, supplier.getPhoneNumber());
    }

    @Test
    void testSetPhoneNo() {
        Supplier supplier = new Supplier(name, phone, address);
        String newPhone = "087654321098";
        
        supplier.setPhoneNo(newPhone);
        assertEquals(newPhone, supplier.getPhoneNumber());
    }

    @Test
    void testSetAddress() {
        Supplier supplier = new Supplier(name, phone, address);
        String newAddress = "Jalan Melati No. 20";
        
        supplier.setAddress(newAddress);
        assertEquals(newAddress, supplier.getAddress());
    }

    @Test
    void testUpdateTimestampOnPreUpdate() throws InterruptedException {
        Supplier supplier = new Supplier(name, phone, address);
        Date initialUpdatedAt = supplier.getUpdatedAt();
        
        // Sleep to ensure time difference
        Thread.sleep(10);
        
        // Trigger @PreUpdate
        supplier.onUpdate();
        
        assertNotEquals(initialUpdatedAt, supplier.getUpdatedAt());
    }

    @Test
    void testNoArgsConstructor() {
        Supplier supplier = new Supplier();
        
        assertNull(supplier.getId());
        assertNull(supplier.getName());
        assertNull(supplier.getPhoneNumber());
        assertNull(supplier.getAddress());
        assertNull(supplier.getCreatedAt());
        assertNull(supplier.getUpdatedAt());
    }

    @Test
    void testAllArgsConstructor() {
        Supplier supplier = new Supplier(id, name, phone, address, createdAt, updatedAt);
        
        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertEquals(createdAt, supplier.getCreatedAt());
        assertEquals(updatedAt, supplier.getUpdatedAt());
    }
}