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
    void testCreateValidSupplier() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        assertEquals(id, supplier.getId());
        assertEquals(name, supplier.getName());
        assertEquals(phone, supplier.getPhoneNumber());
        assertEquals(address, supplier.getAddress());
        assertNotNull(supplier.getCreatedAt());
        assertNotNull(supplier.getUpdatedAt());
    }

    @Test
    void testSetNameToValidName() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        supplier.setName("PT Baru Jaya");
        assertEquals("PT Baru Jaya", supplier.getName());
    }

    @Test
    void testSetNameToNullThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setName(null));
    }

    @Test
    void testSetPhoneNoToBlankThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setPhoneNo("   "));
    }

    @Test
    void testSetPhoneNoToNullThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setPhoneNo(null));
    }


    @Test
    void testSetAddressToNullThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setAddress(null));
    }

    @Test
    void testSetNameToBlankThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setName("   "));
    }

    @Test
    void testSetAddressToBlankThrowsException() {
        Supplier supplier = Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .build();

        assertThrows(IllegalArgumentException.class, () -> supplier.setAddress("   "));
    }

}
