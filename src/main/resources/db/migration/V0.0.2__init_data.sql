insert into brands(name_brand)
values ('Audi'), ('Opel'), ('Volkswagen'), ('Volvo'), ('Dodge');

insert into models(name_model, brand_id)
values ('A3', 1), ('A4', 1), ('Golf', 3), ('V40', 4), ('V60', 4), ('Passat', 3), ('A8', 1),
       ('Vectra', 2), ('Insignia', 2), ('Viper', 5);

insert into cars(model_id, year_production, type_of_fuel, engine_capacity, color)
values (1, 2012, 'PETROL', 1900, 'BLACK'), (2, 2007, 'DIESEL', 2200, 'BVLUE'), (8, 2006, 'LPG', 3400, 'BLACK'),
       (1, 2002, 'LPG', 2000, 'WHITE'), (7, 2020, 'ELECTRIC', 2000, 'WHITE'), (10, 2020, 'ELECTRIC', 4400, 'RED');