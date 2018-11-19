create table purchases(
    purchase_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choco_id int not null,
    user_id int not null,
    purchase_amount int not null,
    purchase_date date,
    CONSTRAINT purch_fk FOREIGN KEY (choco_id) REFERENCES Chocolate (choco_id),
    CONSTRAINT purch_fk2 FOREIGN KEY (user_id) REFERENCES Users (user_id)
);