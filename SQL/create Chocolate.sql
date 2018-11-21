create table chocolate(
    choco_id int not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    choco_name varchar(30) not null,
    choco_desc varchar(255) not null,
    choco_type varchar(30) not null,
    choco_weight varchar(20) not null,
    choco_producer varchar(30) not null,
    choho_image_folder varchar(255) not null,
    choco_date_entered date
);