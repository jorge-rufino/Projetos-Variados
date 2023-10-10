create table tb_foods(
		id serial primary key,
        title text not null,
        image text,
        price integer not null
) engine=InnoDB default charset=utf8;