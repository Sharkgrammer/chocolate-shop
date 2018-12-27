create table users(
    user_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_name varchar(50) not null,
    user_email varchar(50) not null,
    user_password varchar(50) not null,
    user_type varchar(20) not null,
    user_address varchar(255),
    user_key varchar(20)
);