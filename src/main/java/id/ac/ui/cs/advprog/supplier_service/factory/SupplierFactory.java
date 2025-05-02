package id.ac.ui.cs.advprog.supplier_service.factory;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;

import java.util.Date;
import java.util.UUID;

public class SupplierFactory {

    public static Supplier createSupplier(String name, String phoneNumber, String address) {
        return new Supplier(
                UUID.randomUUID(),
                name,
                phoneNumber,
                address,
                new Date(),
                new Date()
        );
    }

    public static Supplier createFromRawData(UUID id, String name, String phone, String address, Date createdAt) {
        return Supplier.builder()
                .id(id)
                .name(name)
                .phoneNumber(phone)
                .address(address)
                .createdAt(createdAt)
                .build();
    }

    public static Supplier createEmptySupplier() {
        return new Supplier(
                UUID.randomUUID(),
                "PlaceholderName",
                "PlaceholderNo",
                "PlaceholderAddress",
                new Date(),
                new Date()
        );
    }
}