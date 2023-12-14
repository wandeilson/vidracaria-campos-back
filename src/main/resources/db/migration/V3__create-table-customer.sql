CREATE TABLE Customer (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255),
                          customer_type VARCHAR(255),
                          cpf_cnpj VARCHAR(20),
                          email VARCHAR(255),
                          phone VARCHAR(20),
                          address VARCHAR(255),
                          zip_code VARCHAR(15),
                          number VARCHAR(10),
                          city VARCHAR(100),
                          state VARCHAR(100),
                          landmark VARCHAR(255),
                          registration_date TIMESTAMP
);
