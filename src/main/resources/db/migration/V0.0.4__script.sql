CREATE TABLE IF NOT EXISTS brands (
	id bigserial NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT brands_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS models (
	id bigserial NOT NULL,
	name varchar(255) NOT NULL,
	brand_id bigint NOT NULL,
	CONSTRAINT models_pkey PRIMARY KEY (id),
    CONSTRAINT brand_model_fk FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE TABLE IF NOT EXISTS cars (
	id bigserial NOT NULL,
	model_id bigint NOT NULL,
	year_production int NOT NULL,
	type_of_fuel varchar(55) NOT NULL,
	engine_capacity int NOT NULL,
	color varchar(55) NOT NULL,
	CONSTRAINT cars_pkey PRIMARY KEY (id),
    CONSTRAINT model_car_fk FOREIGN KEY (model_id) REFERENCES models(id)
);