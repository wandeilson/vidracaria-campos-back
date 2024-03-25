package com.vidracariaCampos.unit.model;
import com.vidracariaCampos.model.entity.Address;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.model.enums.CustomerType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void testCustomerCreation() {
        UUID id = UUID.randomUUID();
        String name = "Test Customer";
        CustomerType customerType = CustomerType.FISICA;
        String cpfcnpj = "123.456.789-00";
        String email = "test@example.com";
        String phone = "+1234567890";
        Address address = new Address("Street 123", "12345678", "123", "City", "State", "Landmark");
        LocalDateTime registrationDate = LocalDateTime.now();

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setCustomerType(customerType);
        customer.setCpfcnpj(cpfcnpj);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setRegistrationDate(registrationDate);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(customerType, customer.getCustomerType());
        assertEquals(cpfcnpj, customer.getCpfcnpj());
        assertEquals(email, customer.getEmail());
        assertEquals(phone, customer.getPhone());
        assertEquals(address, customer.getAddress());
        assertEquals(registrationDate, customer.getRegistrationDate());
    }

    @Test
    void testCustomerDefaultConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer);
    }

//    @Test
//    void testCustomerToString() {
//        UUID id = UUID.randomUUID();
//        String name = "Test Customer";
//        CustomerType customerType = CustomerType.FISICA;
//        String cpfcnpj = "123.456.789-00";
//        String email = "test@example.com";
//        String phone = "+1234567890";
//        Address address = new Address("Street 123", "12345678", "123", "City", "State", "Landmark");
//        LocalDateTime registrationDate = LocalDateTime.now();
//
//        Customer customer = new Customer();
//        customer.setId(id);
//        customer.setName(name);
//        customer.setCustomerType(customerType);
//        customer.setCpfcnpj(cpfcnpj);
//        customer.setEmail(email);
//        customer.setPhone(phone);
//        customer.setAddress(address);
//        customer.setRegistrationDate(registrationDate);
//
//        String expectedToString = "Customer(id=" + id + ", name=Test Customer, customerType=FISICA, cpfcnpj=123.456.789-00, email=test@example.com, phone=+1234567890, address=Address(address=Street 123, zipCode=12345678, number=123, city=City, state=State, landmark=Landmark), registrationDate=" + registrationDate + ")";
//        assertEquals(expectedToString, customer.toString());
//    }

    @Test
    void testCustomerEqualsAndHashCode() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        String name = "Test Customer";
        CustomerType customerType = CustomerType.FISICA;
        String cpfcnpj = "123.456.789-00";
        String email = "test@example.com";
        String phone = "+1234567890";
        Address address = new Address("Street 123", "12345678", "123", "City", "State", "Landmark");
        LocalDateTime registrationDate = LocalDateTime.now();

        Customer customer1 = new Customer();
        customer1.setId(id1);
        customer1.setName(name);
        customer1.setCustomerType(customerType);
        customer1.setCpfcnpj(cpfcnpj);
        customer1.setEmail(email);
        customer1.setPhone(phone);
        customer1.setAddress(address);
        customer1.setRegistrationDate(registrationDate);

        Customer customer2 = new Customer();
        customer2.setId(id1);
        customer2.setName(name);
        customer2.setCustomerType(customerType);
        customer2.setCpfcnpj(cpfcnpj);
        customer2.setEmail(email);
        customer2.setPhone(phone);
        customer2.setAddress(address);
        customer2.setRegistrationDate(registrationDate);

        Customer customer3 = new Customer();
        customer3.setId(id2);
        customer3.setName(name);
        customer3.setCustomerType(customerType);
        customer3.setCpfcnpj(cpfcnpj);
        customer3.setEmail(email);
        customer3.setPhone(phone);
        customer3.setAddress(address);
        customer3.setRegistrationDate(registrationDate);

        assertEquals(customer1, customer2);
        assertNotEquals(customer1, customer3);
        assertEquals(customer1.hashCode(), customer2.hashCode());
        assertNotEquals(customer1.hashCode(), customer3.hashCode());
    }

}
