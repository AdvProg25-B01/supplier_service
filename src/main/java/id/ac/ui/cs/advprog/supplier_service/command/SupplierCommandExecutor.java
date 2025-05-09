package id.ac.ui.cs.advprog.supplier_service.command;

import org.springframework.stereotype.Component;

@Component
public class SupplierCommandExecutor {
    public <T> T execute(SupplierCommand command) {
        return (T) command.execute();
    }
}
