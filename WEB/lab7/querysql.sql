
create table if not exists `recipes`(
id int(8) not null auto_increment,
`author` varchar(255) not null,
`name` varchar(255) not null,
`type` varchar(255) not null,
`recipe` varchar(255)not null,
PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

insert into recipes(id,author,name,type,recipe) values (1,'nicu','tiramisu','desert','dfer');

drop table `recipes`;
select * from recipes;
