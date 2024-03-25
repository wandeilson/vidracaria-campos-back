package com.vidracariaCampos.unit.service;
import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.entity.Address;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.model.enums.CustomerType;
import com.vidracariaCampos.model.repository.CustomerRepositoty;
import com.vidracariaCampos.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest implements ConfigSpringTest {

    @Autowired
    private CustomerRepositoty customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void testSaveCustomer_Success() {
        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");

        Customer savedCustomer = customerService.save(customer);


        assertNotNull(savedCustomer.getId());
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("12345678900", savedCustomer.getCpfcnpj());
        assertEquals("john@example.com", savedCustomer.getEmail());
        assertNotNull(savedCustomer.getRegistrationDate());
    }

//    @Test
//    void testGetAllCustomers_Success() {
//        Customer customer1 = createCustomer("John Doe", "12345678900", "john@example.com");
//        Customer customer2 = createCustomer("Jane Doe", "98765432100", "jane@example.com");
//        customerRepository.saveAll(List.of(customer1, customer2));
//
//        List<Customer> customers = customerService.getAllCustomers();
//
//        assertEquals(2, customers.size());
//    }

//    @Test
//    void testFindById_Success() {
//        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");
//        customerRepository.save(customer);
//
//        Optional<Customer> optionalCustomer = customerService.findById(customer.getId());
//
//        assertTrue(optionalCustomer.isPresent());
//        assertEquals(customer.getName(), optionalCustomer.get().getName());
//        assertEquals(customer.getEmail(), optionalCustomer.get().getEmail());
//    }
    @Test
    void testDeleteCustomer_Success() {
        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");
        customerRepository.save(customer);

        customerService.delete(customer);

        assertFalse(customerRepository.existsById(customer.getId()));
    }

    @Test
    void testExistsByEmail_True() {
        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");
        customerRepository.save(customer);

        assertTrue(customerService.existsByEmail("john@example.com"));
    }

    @Test
    void testExistsByEmail_False() {
        assertFalse(customerService.existsByEmail("nonexistent@example.com"));
    }
    @Test
    void testSaveCustomer_CustomerAlreadyExists() {
        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");
        customerRepository.save(customer);

        Customer duplicateCustomer = createCustomer("John Doe", "12345678900", "duplicate@example.com");

        assertThrows(RuntimeException.class, () -> customerService.save(duplicateCustomer));
    }

//    @Test
//    void testFindById_CustomerNotFound() {
//        UUID nonExistentCustomerId = UUID.randomUUID();
//
//        assertThrows(NoSuchElementException.class, () -> customerService.findById(nonExistentCustomerId).orElseThrow());
//    }

    @Test
    void testDeleteCustomer_CustomerNotFound() {
        UUID nonExistentCustomerId = UUID.randomUUID();
        Customer nonExistentCustomer = new Customer();
        nonExistentCustomer.setId(nonExistentCustomerId);

        assertThrows(IllegalArgumentException.class, () -> customerService.delete(nonExistentCustomer));
    }

    @Test
    void testExistsByEmail_CustomerExists() {
        Customer customer = createCustomer("John Doe", "12345678900", "john@example.com");
        customerRepository.save(customer);

        assertTrue(customerService.existsByEmail("john@example.com"));
    }

    @Test
    void testExistsByEmail_CustomerNotExists() {
        assertFalse(customerService.existsByEmail("nonexistent@example.com"));
    }
    private Customer createCustomer(String name, String cpfCnpj, String email) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setCpfcnpj(cpfCnpj);
        customer.setEmail(email);
        customer.setRegistrationDate(LocalDateTime.now());
        customer.setCustomerType(CustomerType.FISICA);
        customer.setAddress(new Address());
        return customer;
    }
}
