create table Review(
    rev_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choco_id int not null,
    user_id int not null,
    rev_date date not null,
    rev_data varchar(255),
    rev_title varchar(255),
    rev_postive boolean,
    CONSTRAINT rev_fk FOREIGN KEY (choco_id) REFERENCES chocolate (choco_id),
    CONSTRAINT rev_fk2 FOREIGN KEY (user_id) REFERENCES Users (user_id)
);