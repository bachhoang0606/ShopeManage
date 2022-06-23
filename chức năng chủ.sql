--** Sản phẩm 
-- Tạo 1 view để xem sản phẩm
create view viewSP as
	select product_id,product_name, size, price, title, category_name, actor_name
		from products natural join actor natural join categories;

-- Tạo index trên tên sp và loại sp
create index idx_prod_name on products using btree(product_name);
create index idx_prod_loai on products using btree(category_id);

-- tìm Kiếm toàn bộ sản phẩm trong kho
select * from viewSP order by product_id;

-- Tìm 1 sản phẩm trong kho trên view
select * from viewSP where product_name = 'áo phông';

-- Tìm kiếm theo loại sản phẩm
select * from viewSP where category_name = 'áo';

-- function cập nhật lại số lượng sản phẩm không có trong bảng kho

create or replace function updateSoLuongSP()
returns void as
$$
declare 
	declare i products%rowtype;
begin 
	for i in (select product_id from products where product_id not in (select product_id from inventorys_product))
	loop 
		insert into inventorys_product values (i.product_id,0,0);
	end loop;
end;
$$
language plpgsql;

create or replace function themSP()
returns trigger as
$$
declare 
	maLSP int;
	maNSX int;
begin 
	maLSP = (select category_id from categories where category_name = new.category_name);
	maNSX = (select actor_id from actor where actor_name = new.actor_name);
	if maLSP is null or maNSX is null then
		return old;
	else 
		insert into products (product_name,size,price,title,category_id,actor_id)
			values(new.product_name,new.size,new.price,new.title,maLSP,maNSX);
		PERFORM updateSoLuongSP();
		return new;
	end if;
end;
$$
language plpgsql;

create trigger tg_bf_themsp
instead of insert on viewSP
for each row 
execute procedure themSP();

-- chức năng update thông tin sản phẩm vào view xemSP
create or replace function suaSP()
returns trigger as
$$
declare 
	maLSP int;
	maNSX int;
begin 
	maLSP = (select category_id from categories where category_name = new.category_name);
	maNSX = (select actor_id from actor where actor_name = new.actor_name);
 	if maLSP is null or maNSX is null then
		return null;
	else 
		update products set product_name = new.product_name, size = new.size,price = new.price
			,title = new.title,category_id = maLSP, actor_id = maNSX
			where product_id = old.product_id;
		return new;
	end if;
end;
$$
language plpgsql;

create trigger tg_it_suasp
instead of update on viewSP
for each row 
execute procedure suaSP();

--** Thống kê doanh số view
-- sản phẩm bán chạy bán chạy trong tháng (bán chậm tương tự)
with tmp as(
	select v.product_name, size,actor_name,sum(quantily) as so_luong_ban
		from viewsp v join orderline o on (v.product_id = o.product_id)
		join bill b on (b.bill_id = o.bill_id)
		where extract('year' from b.bill_date ) ='2021' and extract('month' from b.bill_date ) = '12'
		group by v.product_id,v.product_name,size,actor_name
)select * from tmp where so_luong_ban in (select max(so_luong_ban) from tmp);

-- Doanh Số bán trong 1 tháng
select sum(bill_money) from bill 
	where extract('year' from bill_date) = '2021' and extract('month' from bill_date) = '12';

--** Thông tin khách hàng
--Xem toàn bộ danh sách khách hàng
select customer_id,first_name || ' ' || last_name as full_name,dob,gender,email,phone, address from customer;

--Tìm khách hàng theo tên
select customer_id,first_name || ' ' || last_name as full_name,dob,gender,email,phone, address 
	from customer where first_name = 'Lã Văn' and last_name = 'Lam';

-- Tìm kiếm các hóa đơn của khách hàng được chỉ định bằng ID khách hàng
select bill_id, bill_date, netamount, bill_money, feedback, feedback_date 
	from bill where customer_id = 4;

--Hiển thị chi tiết hóa đơn của khách hàng được chỉ định bằng mã hóa đơn 
select product_name,quantily,size, price,actor_name from orderline o, viewsp v
	where o.product_id = v.product_id and bill_id = 14 ;

--** Quản lý nhân viên
-- Xem toàn bộ danh sách nhân viên
select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary from staff;

-- Lấy nhân viên theo tên
select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary 
	from staff where first_name = 'Đỗ Quang' and last_name = 'Huy';
		
-- Lấy hóa đơn nhân viên tạo theo mã nhân viên
select imbill_id ,imbill_date, state, netamount, imbill_money,actor_name 
	from import_bill ib,staff s,actor a
		where ib.staff_id = s.staff_id and ib.actor_id = a.actor_id
			and s.staff_id = 1;

-- Lấy sản phẩm trong hóa đơn nhân viên tạo
select product_name,size,quantity from import_order_line iol, viewsp v
	where iol.product_id = v.product_id and imbill_id = 5;

-- Xem toàn bộ thay đổi dữ liệu trong kho
select c.staff_id,c.product_id,first_name ||' '|| last_name as full_name, date_change,product_name,size,actor_name
	from staff s, viewsp v,change c where c.product_id = v.product_id and s.staff_id = c.staff_id 
		and c.staff_id = 1;

-- Xem toàn bộ hóa đơn đã nhập từ nhà sản xuất
select imbill_id ,imbill_date, state, netamount, imbill_money,actor_name,ib.staff_id
	from import_bill ib,staff s,actor a
		where ib.staff_id = s.staff_id and ib.actor_id = a.actor_id
			order by imbill_date;

-- Xem thông tin nhân viện tạo đã hóa đơn
select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary 
	from staff where staff_id = 1;

--Thông tin nhân viên chi tiết bao gồm tài khoản mật khẩu
select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary,usename, password 
	from staff;

--Thêm Nhân Viên Mới
insert into staff (first_name, last_name, dob, gender, email,phone,address,usename,password,salary) values 
	('Bách','Hoàng Xuân','2001/06/06','M','0606bach@mail.com','0111111','Nam Định', 'bach123','111111','100');

--Thu hồi tài  khoản nhân viên
select count(*) from staff where usename = '12202218';
update staff set usename = '12202218', password = '12202218' where staff_id = 5;

-- Cập nhật lại lương nhân viên
update staff set salary = 60 where staff_id = 1;

--** Quản lý danh sách nhà cung cấp
--Xem toàn bộ nhà cung cấp
select * from actor;

-- Tìm nhà cung cấp theo tên
select * from actor where actor_name = 'ABC shop';

--Thêm nhà cung cấp mới
insert into actor (actor_name,hotline,address) 
	values ('Tien phong','097846868','quat lam GT/ND');
	
--Cập nhật thông tin nhà cung cấp
update actor set actor_name = 'mewhoww', hotline = '09876543', address = 'xuan truong HH'
	where actor_id = '1';
	
--** Quản lý danh sách loại sản phẩm
-- Xem toàn bộ loại sản phẩm
select * from categories;

-- Tìm loại sản phẩm theo tên
select * from categories where category_name = 'áo'

--Thêm loại sản phẩm mới
insert into categories (category_name) values('ao');

--Cập nhật loại sản phẩm 
update categories set category_name = 'adu' where category_id = 1;  
