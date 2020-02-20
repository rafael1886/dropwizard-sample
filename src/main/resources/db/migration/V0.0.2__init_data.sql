insert into brands(name_brand)
values ('Audi'), ('Opel'), ('Volkswagen'), ('Volvo');

insert into models(name_model, brand_id)
values ('A3', 1), ('A4', 1), ('Golf', 3), ('Astra', 2);

insert into cars(model_id, year_production, type_of_fuel, engine_capacity, color)
values (1, 2012, 'PETROL', 1900, 'BLACK'), (2, 2007, 'PETROL', 2200, 'Blue'), (1, 2002, 'PETROL', 2000, 'WHITE');