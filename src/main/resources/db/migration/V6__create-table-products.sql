CREATE TABLE product (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255),
                          height FLOAT,
                          width FLOAT,
                          depth FLOAT,
                          unitOfMeasure VARCHAR(255),
                          price DECIMAL(10, 2),
                          category VARCHAR(255),
                          registration_date TIMESTAMP
);

