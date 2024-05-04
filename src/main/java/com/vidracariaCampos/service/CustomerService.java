package com.vidracariaCampos.service;

import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.repository.CustomerRepositoty;
import com.vidracariaCampos.security.UserTolls;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private CustomerRepositoty customerRepositoty;
    @Autowired
    public CustomerService (CustomerRepositoty customerRepositoty){
        this.customerRepositoty = customerRepositoty;
    }

    public Customer save(CustomerDTO customerDTO)  throws Exception {
        UUID idUser = UserTolls.getUserContextId();

        verifyEmail(customerDTO.email(), idUser);
        verifyCpfCnpj(customerDTO.cpfcnpj(), idUser);

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setIdUser(idUser);
        customer.setRegistrationDate(LocalDateTime.now());
        return customerRepositoty.save(customer);
    }

    public Customer update(CustomerDTO customerDTO, UUID id)  throws Exception {
        UUID idUser = UserTolls.getUserContextId();

        Customer customer = customerRepositoty.findByIdAndIdUser(id, idUser)
                .orElseThrow(() -> new Exception("Customer not found"));

        if(!customerDTO.email().equals(customer.getEmail()))
            verifyEmail(customerDTO.email(), idUser);

        if(!customerDTO.cpfcnpj().equals(customer.getCpfcnpj()))
            verifyCpfCnpj(customerDTO.cpfcnpj(), idUser);

        if(customerRepositoty.existsByEmail(customerDTO.email()) ||
                customerRepositoty.existsAllByCpfcnpj(customerDTO.cpfcnpj())){

            new Exception("email or CPF/CNPJ is already in use!");
        }

        BeanUtils.copyProperties(customerDTO, customer);
        return customerRepositoty.save(customer);
    }

    public List<Customer> getAllCustomers() throws Exception {
        return customerRepositoty.findCustomersByUserId(UserTolls.getUserContextId());
    }

    public Customer findById(UUID id) throws Exception{
        return customerRepositoty.findByIdAndIdUser(id, UserTolls.getUserContextId())
                .orElseThrow(() -> new Exception("Customer not found"));
    }

    public void deleteById(UUID id) throws Exception{
        findById(id);
        customerRepositoty.deleteByIdAndIdUser(id, UserTolls.getUserContextId());
    }

    public void verifyEmail(String email, UUID idUser) throws Exception {
        if (StringUtils.isNotEmpty(email) && customerRepositoty.existsByEmailAndIdUser(email, idUser)) {
            throw new Exception("Conflict: Email is already in use!");
        }
    }

    public void verifyCpfCnpj(String cpfCnpj, UUID idUser) throws Exception {
        if (StringUtils.isNotEmpty(cpfCnpj) && customerRepositoty.existsByCpfcnpjAndIdUser(cpfCnpj, idUser)) {
            throw new Exception("Conflict: CPF/CNPJ is already in use!");
        }
    }

}
