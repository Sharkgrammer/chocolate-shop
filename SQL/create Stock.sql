create table stocks(
    stock_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choco_id int not null,      
    stock_amount int not null,
    stock_date_entered date not null,
    CONSTRAINT stock_fk FOREIGN KEY (choco_id) REFERENCES Chocolate (choco_id)
);