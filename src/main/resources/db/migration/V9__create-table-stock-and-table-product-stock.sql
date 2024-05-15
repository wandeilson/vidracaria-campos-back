CREATE TABLE stock (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid()
);

CREATE TABLE product_stock (
                               id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                               stock_id UUID REFERENCES stock(id) ON DELETE CASCADE,
                               id_product UUID NOT NULL,
                               actual_quantity INT DEFAULT 0,
                               total_entries INT DEFAULT 0,
                               total_exits INT DEFAULT 0
);
