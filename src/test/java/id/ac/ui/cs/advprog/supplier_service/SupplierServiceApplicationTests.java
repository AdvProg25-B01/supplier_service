package id.ac.ui.cs.advprog.supplier_service;

import id.ac.ui.cs.advprog.supplier_service.command.SupplierCommandExecutor;
import id.ac.ui.cs.advprog.supplier_service.repository.SupplierRepository;
import id.ac.ui.cs.advprog.supplier_service.service.AsyncSupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.Executor;

@SpringBootTest
@ActiveProfiles("test")
class SupplierServiceApplicationTests {

    @MockBean
    private SupplierRepository supplierRepository;

    @MockBean
    private SupplierCommandExecutor commandExecutor;

    @MockBean
    private AsyncSupplierService asyncSupplierService;

    @MockBean(name = "taskExecutor")
    private Executor taskExecutor;

    @Test
    void contextLoads() {
        SupplierServiceApplication.main(new String[0]);
    }

}
