package com.vidracariaCampos.unit.service;

import com.vidracariaCampos.config.ConfigSpringTest;
import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.model.entity.Address;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.model.entity.User;
import com.vidracariaCampos.model.enums.CustomerType;
import com.vidracariaCampos.model.enums.Role;
import com.vidracariaCampos.repository.CustomerRepositoty;
import com.vidracariaCampos.repository.UserRepository;
import com.vidracariaCampos.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest implements ConfigSpringTest {

    @Autowired
    private CustomerRepositoty customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerService customerService;
    private User user;
    private CustomerDTO customerDTO;
    private Customer savedCustomer;

    @BeforeEach
    void setUp() throws Exception {
        customerRepository.deleteAll();

        String email = "test@example.com";
        user = new User(null, "Test User", "test@example.com", Role.ADMIN, "ADMIN");
        user.setEmail(email);

        user = (User) userRepository.save(user);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        customerDTO = createCustomerDTO(null,"John Doe", "12345678900", "john@example.com");
        savedCustomer = customerService.save(customerDTO);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testSaveCustomer_Success() {
        assertNotNull(savedCustomer.getId());
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("12345678900", savedCustomer.getCpfcnpj());
        assertEquals("john@example.com", savedCustomer.getEmail());
        assertNotNull(savedCustomer.getRegistrationDate());
    }

    @Test
    @Order(2)
    void testUpdateCustomer_Success() throws Exception {
        customerDTO = createCustomerDTO(savedCustomer.getId(),"Jane",customerDTO.cpfcnpj(), customerDTO.email());
        Customer updatedCustomer = customerService.update(customerDTO, savedCustomer.getId());
        assertEquals("Jane", updatedCustomer.getName());
    }

    @Test
    void testDeleteCustomer_Success() throws Exception {
        customerService.deleteById(savedCustomer.getId());
        assertFalse(customerRepository.existsById(savedCustomer.getId()));
    }

    @Test
    void testVerifyEmail_Conflict() {
        assertThrows(Exception.class, () -> customerService.verifyEmail("john@example.com", savedCustomer.getIdUser()));
    }

    @Test
    void testVerifyCpfCnpj_Conflict() {
        assertThrows(Exception.class, () -> customerService.verifyCpfCnpj("12345678900", savedCustomer.getIdUser()));
    }
    @Test
    void testUpdateCustomer_NotFound() {
        UUID nonExistentId = UUID.randomUUID();
        CustomerDTO updatedCustomerDTO = createCustomerDTO(null,"Jane", "12345678900", "jane@example.com");
        assertThrows(Exception.class, () -> customerService.update(updatedCustomerDTO, nonExistentId));
    }

    @Test
    void testDeleteCustomer_NotFound() {
        UUID nonExistentId = UUID.randomUUID();
        assertThrows(Exception.class, () -> customerService.deleteById(nonExistentId));
    }

    @Test
    void testSaveCustomer_DuplicateEmail() {
        CustomerDTO duplicateCustomerDTO = createCustomerDTO(null,"John", "12345678900", "john@example.com");
        assertThrows(Exception.class, () -> customerService.save(duplicateCustomerDTO));
    }

    @Test
    void testSaveCustomer_DuplicateCpfCnpj() {
        CustomerDTO duplicateCustomerDTO = createCustomerDTO(null,"John", savedCustomer.getCpfcnpj(), "john@example.com");
        assertThrows(Exception.class, () -> customerService.save(duplicateCustomerDTO));
    }


    private Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.name());
        customer.setCpfcnpj(customerDTO.cpfcnpj());
        customer.setEmail(customerDTO.email());
        customer.setRegistrationDate(LocalDateTime.now());
        customer.setCustomerType(CustomerType.FISICA);
        return customer;
    }

    private CustomerDTO createCustomerDTO(UUID id,String name, String cpfCnpj, String email) {
        CustomerDTO customerDTO = new CustomerDTO(id,name,CustomerType.FISICA,cpfCnpj,email,
                new Address("dfgdf","esdfsdf","efdsdf","sdff","dfsdf","oisdufso"));

        return customerDTO;
    }

}
