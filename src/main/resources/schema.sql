-- Now we will comment(ignore) this schema and let hibernate automaticaly create schema from entities anotation.
-- If we want to use this schema we should make changes to entities anotation too.
-- Here is how to do this: https://attacomsian.com/blog/spring-data-jpa-many-to-many-mapping
/*
create table if not exists Taco_Order (
  id bigint NOT NULL,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null,
  PRIMARY KEY(id)  
);

create table if not exists Taco (
  id int NOT NULL,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null,
  PRIMARY KEY(id),
  FOREIGN KEY (taco_order) REFERENCES Taco_Order(id)
);
*/
/*
create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null,
  PRIMARY KEY(id)
);

create table if not exists Ingredient_Ref (
  ingredient varchar(4) not null,
  taco bigint not null,
  taco_key bigint not null,
  FOREIGN KEY (ingredient) REFERENCES Ingredient(id)
);

*/

-- Another way to add foreign keys. 
-- This way there is no difference what order tables are created !!!
-- https://www.w3schools.com/sql/sql_foreignkey.asp
/* 
alter table Taco
  add foreign key (taco_order) references Taco_Order(id);
alter table Ingredient_Ref
  add foreign key (ingredient) references Ingredient(id); */