package com.vidracariaCampos.controller;

import com.vidracariaCampos.model.dto.CustomerDTO;
import com.vidracariaCampos.model.entity.Customer;
import com.vidracariaCampos.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Object> createCustomer(@RequestBody @Valid CustomerDTO customerDTO){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        if(customerDTO.email() != null){
            if(customerService.existsByEmail(customerDTO.email(),idUser)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
            }
        }
        if (customerDTO.cpfcnpj() != null){
            if(customerService.existsByCpf_cnpj(customerDTO.cpfcnpj(), idUser)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF or CNPJ is already in use!");
            }
        }
        var customerEntity = new Customer();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerEntity.setIdUser(idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(customerEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable (value = "id") UUID id,
                                                 @RequestBody @Valid CustomerDTO customerDTO){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        Optional<Customer> customerOptional;
        if(!customerService.existsByIdAndIdUser(id, idUser)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else{
            customerOptional = customerService.findById(id, idUser);
            if(customerService.existsByEmail(customerDTO.email(), idUser)){//Se ja existir um customer com esse email
                /*Se o email já existe (if acima) e esse email do DTO NÃO é igual ao do customer que ele quer atualizar,
                * quer dizer que já tem alguem usando esse email. */
                if(!customerDTO.email().equals(customerOptional.get().getEmail())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
                }
            }
        }
        if (customerDTO.cpfcnpj() != null){
            if(customerService.existsByCpf_cnpj(customerDTO.cpfcnpj(), idUser)){
                if(!customerDTO.cpfcnpj().equals(customerOptional.get().getCpfcnpj())){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF or CNPJ is already in use!");
                }
            }
        }

        var customerEntity = new Customer();
        BeanUtils.copyProperties(customerDTO, customerEntity);
        customerEntity.setRegistrationDate(customerOptional.get().getRegistrationDate());
        customerEntity.setId(customerOptional.get().getId());
        customerEntity.setIdUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updade(customerEntity));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers(AuthenticationController.getUserLogged().getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable (value = "id") UUID id){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        Optional<Customer> customerOptional = customerService.findById(id, idUser);
        if(!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomerById (@PathVariable (value = "id") UUID id){
        UUID idUser = AuthenticationController.getUserLogged().getId();
        Optional<Customer> optionalCustomer = customerService.findById(id, idUser);
        if(!optionalCustomer.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        }
        customerService.deleteById(id, idUser);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully.");

    }


}
