create table Activity(
    activity_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_id int not null,
    activity_type int not null,
    activity_details varchar(30),
    CONSTRAINT activity_fk FOREIGN KEY (user_id) REFERENCES Users (user_id)
);