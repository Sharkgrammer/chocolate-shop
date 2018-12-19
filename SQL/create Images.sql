create table image(
    img_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choco_id int not null,
    img_img blob,
    CONSTRAINT img_fk FOREIGN KEY (choco_id) REFERENCES chocolate (choco_id)
);