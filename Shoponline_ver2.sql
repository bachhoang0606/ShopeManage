create table import_bill_line
(
	imbill_line_id char(6) not null GENERATED ALWAYS AS IDENTITY,
	imbill_id char(4) not null,
	product_id char(4) not null,
	quantity int,
	constraint  il_pk primary key(imbill_line_id)
);
create table import_bill
(
	imbill_id char(4) not null GENERATED ALWAYS AS IDENTITY,
	imbill_date date,
	state char(1),
	netamount int,
	imbill_money money,
	actor_id char(4) not null,
	--staff_id char(4) not null, hơi thừa
	constraint ib_pk primary key(imbill_id)
);
create table build_imbill
(
	date_build date,
	time_build time,
	imbill_id char(4) not null,
	staff_id char(4) not null,
	constraint db_pk primary key(date_build,time_build)
);
create table staff
(
	staff_id char(4) not null GENERATED ALWAYS AS IDENTITY,
	first_name varchar(20),
	last_name varchar(20),
	dob date,
	gender char(1),
	email varchar(20),
	phone char(9),
	address varchar(20),
	usename varchar(10) not null,
	password varchar(10) not null,
	mission varchar(10),
	salary money,
	constraint sid_pk primary key(staff_id)
);
create table change
(
	date_change date not null,
	staff_id char(4) not null,
	product_id char(4) not null,
	constraint dc_pk primary key(date_change)
);
--foreign key
alter table import_bill_line add constraint ib_chk foreign key (imbill_id) references import_bill(imbill_id);
alter table build_imbill add constraint bib_chk foreign key (imbill_id) references import_bill(imbill_id);
alter table build_imbill add constraint bs_chk foreign key (staff_id) references staff(staff_id);
alter table change add constraint ch_chk foreign key (staff_id) references staff(staff_id);
--bang cua bach
create table products (
	product_id int not NULL GENERATED ALWAYS AS IDENTITY,
	product_name VARCHAR(20) not NULL,
	size VARCHAR(2),
	price money not NULL,
	title text,
	category_id int not null,
	actor_id int not null,
	CONSTRAINT products_pk PRIMARY KEY (product_id)
);
create table categories (
	category_id int not null GENERATED ALWAYS AS IDENTITY,
	category_name varchar(20) not null,
	constraint categories_pk primary key (category_id)
);
create table actor (
	actor_id int not null GENERATED ALWAYS AS IDENTITY,
	actor_name varchar(20) not null,
	hotline char(9),
	address varchar(20),
	constraint actor_pk primary key (actor_id)
);
create table inventorys_product (
	product_id int not null,
	inventory_product int,
	sale_product int,
	constraint inventorys_product_pk primary key (product_id)
);
-- foreign key
alter table products add constraint products_fk_categories foreign key (category_id) references categories(category_id);
alter table products add constraint products_fk_actor foreign key (actor_id) references actor(actor_id);
alter table inventorys_product add constraint inventorys_produc_fk_products foreign key (product_id) references products(product_id);
alter table import_bill add constraint import_fk_actor foreign key (actor_id) references actor(actor_id);
--insert table
-- actor table
insert into actor (actor_name,hotline,address) values ('Tien phong','097846868','quat lam GT/ND');
insert into actor (actor_name,hotline,address) values ('ABC shop','098846868','giap bat HN');
insert into actor (actor_name,hotline,address) values ('banh trang tron','03897641','truc ninh HH/ND');
insert into actor (actor_name,hotline,address) values ('mewhow','09876543','xuan truong HH');
-- categories table
insert into categories (category_name) values('ao');
insert into categories (category_name) values('quan');
insert into categories (category_name) values('gang tay');
insert into categories (category_name) values('vay');
insert into categories (category_name) values('mu');
insert into categories (category_name) values('tat');
insert into categories (category_name) values('khan');
--	products table
insert into products (product_name,size,price,title,category_id,actor_id)
	values('ao phong','M','20$',null,1,1);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('ao ni','l','10$',null,1,1);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('ao khoac','M','15',null,1,1);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('ao len','XL','20$',null,1,1);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('quan vai','XL','25$',null,2,2);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('quan bo','M','5$',null,2,2);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('quan the thao','L','30$',null,2,2);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('quan ngan','M','5$',null,2,2);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('vay cong so','M','30$',null,4,3);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('vay da hoi','M','35$',null,4,3);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('mu vai','M','15$',null,5,4);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('gang thoi trang','L','10$',null,3,4);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('tat thoi trang','M','5$',null,6,3);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('khan len','XL','10$',null,7,4);
insert into products (product_name,size,price,title,category_id,actor_id)
	values('khan doi','XL','10$',null,7,4);
-- inventoyrs_product table
insert into inventorys_product values(1,51,371);
insert into inventorys_product values(16,111,297);
insert into inventorys_product values(3,463,473);
insert into inventorys_product values(4,655,185);
insert into inventorys_product values(5,66,316);
insert into inventorys_product values(6,51,453);
insert into inventorys_product values(7,359,949);
insert into inventorys_product values(8,0,0);
insert into inventorys_product values(9,856,500);
insert into inventorys_product values(10,148,922);
insert into inventorys_product values(11,932,232);
insert into inventorys_product values(12,636,746);
insert into inventorys_product values(13,0,0);
insert into inventorys_product values(14,251,766);
insert into inventorys_product values(15,9,126);
--staff
insert into staff(first_name,last_name,usename,password) values ('luu','hiep','hiep2309','2309');
insert into staff(first_name,last_name,usename,password) values ('hoang','bach','bachdz','bach');
insert into staff(first_name,last_name,usename,password) values ('do','thanh','thanhdz','thanh');
--import_bill
insert into import_bill(actor_id,staff_id) values ('1','1','1');
insert into import_bill(actor_id,staff_id) values ('2','2','1');
insert into import_bill(actor_id,staff_id) values ('3','3','1');
insert into import_bill(actor_id,staff_id) values ('4','1','2');
insert into import_bill(actor_id,staff_id) values ('5','2','2');
--import_bill_line
insert into import_bill_line(imbill_id,product_id) values ('1','1');
insert into import_bill_line(imbill_id,product_id) values ('1','2');
insert into import_bill_line(imbill_id,product_id) values ('1','3');
insert into import_bill_line(imbill_id,product_id) values ('2','4');
insert into import_bill_line(imbill_id,product_id) values ('2','5');
insert into import_bill_line(imbill_id,product_id) values ('3','6');
insert into import_bill_line(imbill_id,product_id) values ('3','7');
insert into import_bill_line(imbill_id,product_id) values ('4','8');
insert into import_bill_line(imbill_id,product_id) values ('4','9');
insert into import_bill_line(imbill_id,product_id) values ('5','10');
insert into import_bill_line(imbill_id,product_id) values ('5','11');
insert into import_bill_line(imbill_id,product_id) values ('5','12');
insert into import_bill_line(imbill_id,product_id) values ('5','13');
insert into import_bill_line(imbill_id,product_id) values ('5','14');
insert into import_bill_line(imbill_id,product_id) values ('5','15');
--build_imbill
insert into build_imbill(date_build,time_build,imbill_id,staff_id) values ('2022-01-09','16:32:00','1','1');
insert into build_imbill(date_build,time_build,imbill_id,staff_id) values ('2022-01-10','17:20:00','2','1');
insert into build_imbill(date_build,time_build,imbill_id,staff_id) values ('2022-01-11','18:01:00','3','1');
insert into build_imbill(date_build,time_build,imbill_id,staff_id) values ('2022-01-12','15:45:00','4','2');
insert into build_imbill(date_build,time_build,imbill_id,staff_id) values ('2022-01-13','16:23:00','5','2');
--change
insert into change(date_change,staff_id,product_id) values ('2022-01-15','2','2');
insert into change(date_change,staff_id,product_id) values ('2022-01-16','1','1');


--THANH
create table customer (
	customer_id int not NULL GENERATED ALWAYS AS IDENTITY,
	first_name VARCHAR(20) ,
	last_name VARCHAR(20) ,
	dob date ,
	gender char(1),
	email VARCHAR(20),
	phone char(9),
	address varchar(20),
	username varchar(10) not NULL,
	pass_word varchar(20) not NULL,
	CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

create table bill (
	bill_id int not NULL GENERATED ALWAYS AS IDENTITY,
	bill_date date ,
	netamount int,
	bill_money money,
	customer_id int not NULL,
	feedback text,
	feedback_date date,
	constraint bill_pk primary key (bill_id),
	CONSTRAINT bill_fk foreign KEY (customer_id) REFERENCES customer(customer_id)
);

create table orderline (
	orderline_id int not null GENERATED ALWAYS AS IDENTITY,
	bill_id char(4) not null,
	product_id char(4) not null,
	quantily int not null,
	constraint orderline_pk primary key (orderline_id)
);


-- insert into 
-- Customer
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Lã Văn','Lam','1-1-1991','M','LamLV@gmail.com','093715246','Hưng Yên','lam','Lam111991');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đỗ Tú','Quyên','3-12-1995','F','QuyenDT@gmail.com','012374897','Hà Nam','quyen','quyen3121995');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Phùng Văn','Tèo','15-12-1992','M','TeoPV@gmail.com','032041248','Vĩnh Phúc','teo','teo15121992');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đỗ Thị','Trâm Anh','7-10-1998','F','AnhDTT@gmail.com','012623740','Hà Nội','anh','anh7101998');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Lê Văn','Lương','23-12-1999','M','LuongLV@gmail.com','057241371','Hải Phòng','luong','Luong23121999');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đỗ Đình','Duy','14-2-1994','M','DuyDD@gmail.com','018975726','Lạng Sơn','duy','duy1421994');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Lê Phương','Nam','12-3-1996','M','NamLP@gmail.com','014286767','Cà Mau','nam','nam1231996');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đào Duy','Huy','21-10-1997','M','HuyDD@gmail.com','012875371','Quảng Ninh','huy','huy21101997');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đinh Văn','Đức','15-9-1996','M','DucDV@gmail.com','097752343','Nam Định','duc','duc1591996');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Nguyễn Hồng','Liên','21-11-1998','F','LienNH@gmail.com','097243437','Hải Dương','lien','Lam111991');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Phạm Đức','Long','27-12-2000','M','LongPD@gmail.com','097833245','Thanh Hóa','long','long27122000');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Nguyễn Văn','Ba','24-1-2001','M','BaNV@gmail.com','018737834','Nghệ An','ba','ba2412001');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Đào Thị','Duyên','2-5-1991','F','DuyenDT@gmail.com','013873786','Hà Tĩnh','duyen','duyen251991');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Nguyễn Bá','Trọng','7-9-1990','M','TrongNB@gmail.com','018782543','Đà Nẵng','trong','trong791990');
insert into customer (first_name,last_name,dob,gender,email,phone,address, username,pass_word) values ('Nguyễn Khánh','Đan','15-12-1989','F','DanNK@gmail.com','024271876','Nha Trang','dan','dan15121989');
select * from customer 
-- bill
insert into bill (bill_date,netamount,bill_money,customer_id,) values ('16-12-2021','2','30$','7')
insert into bill (bill_date,netamount,bill_money,customer_id,) values ('15-12-2021','2','30$','8')
insert into bill (bill_date,netamount,bill_money,customer_id,) values ('14-12-2021','2','30$','9')
insert into bill (bill_date,netamount,bill_money,customer_id,) values ('13-12-2021','2','30$','3')
insert into bill (bill_date,netamount,bill_money,customer_id,) values ('12-12-2021','2','30$','5')
insert into bill (bill_date,netamount,bill_money,customer_id,feeback,feeback_date) values ('11-12-2021','2','30$','8','great','16-12-2021')
insert into bill (bill_date,netamount,bill_money,customer_id,feeback,feeback_date) values ('10-12-2021','2','30$','4','wonderful','16-12-2021')
insert into bill (bill_date,netamount,bill_money,customer_id,feeback,feeback_date) values ('9-12-2021','2','30$','2','great','15-12-2021')
insert into bill (bill_date,netamount,bill_money,customer_id,feeback,feeback_date) values ('8-12-2021','2','30$','4','wonderful','13-12-2021')
insert into bill (bill_date,netamount,bill_money,customer_id,feeback,feeback_date) values ('7-12-2021','2','30$','3','great','17-12-2021')
-- orderline
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')
insert into orderline (bill_id,product_id,quantily) values ('4','','2')

