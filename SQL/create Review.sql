create table Review(
    rev_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_id int not null,
    rev_date date not null,
    rev_data varchar(255),
    CONSTRAINT rev_fk FOREIGN KEY (user_id) REFERENCES Users (user_id)
);