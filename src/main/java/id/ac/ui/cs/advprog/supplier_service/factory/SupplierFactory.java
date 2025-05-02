package id.ac.ui.cs.advprog.supplier_service.factory;

import id.ac.ui.cs.advprog.supplier_service.model.Supplier;

import java.util.Date;
import java.util.UUID;

public class SupplierFactory {

    public static Supplier createSupplier(String name, String phoneNumber, String address) {
        return null;
    }

    public static Supplier createFromRawData(UUID id, String name, String phone, String address, Date createdAt) {
        return null;
    }

    public static Supplier createEmptySupplier() {
        return null;
    }
}