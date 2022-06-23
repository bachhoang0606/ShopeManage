package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NhanVienModel {
	private String[][] nhanVien;
	private String[][] hoaDon;
	private String[][] sanPhamHoaDon;
	private String[][] thayDoi;
	private Connection connection;
	
	public NhanVienModel() throws SQLException {
		this.createConnection();
	}
	
	
	public String[][] getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(String[][] nhanVien) {
		this.nhanVien = nhanVien;
	}


	public String[][] getHoaDon() {
		return hoaDon;
	}


	public void setHoaDon(String[][] hoaDon) {
		this.hoaDon = hoaDon;
	}


	public String[][] getSanPhamHoaDon() {
		return sanPhamHoaDon;
	}


	public void setSanPhamHoaDon(String[][] sanPhamHoaDon) {
		this.sanPhamHoaDon = sanPhamHoaDon;
	}


	public String[][] getThayDoi() {
		return thayDoi;
	}


	public void setThayDoi(String[][] thayDoi) {
		this.thayDoi = thayDoi;
	}


	public void createConnection() throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("-------- PostgreSQL JDBC Connection Testing shop online finaly------------");

		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		connection = null;
		
		// Open connection
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/onlineshop", "postgres","10026060");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} 
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public void xemToanBoNhanVien() throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  ="select count(*) from staff;" ;
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary from staff;";
					result = statement.executeQuery(sqlstatement);
					this.nhanVien = new String[row][8];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							nhanVien[i][0] = result.getString(1);
							nhanVien[i][1] = result.getString(2);
							nhanVien[i][2] = result.getString(3);
							nhanVien[i][3] = result.getString(4);
							nhanVien[i][4] = result.getString(5);
							nhanVien[i][5] = result.getString(6);
							nhanVien[i][6] = result.getString(7);
							nhanVien[i][7] = result.getString(8);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void timNhanVienTheoTen(String textField_searchTenNhanVien) throws SQLException {
		 Statement statement = null;
		 	try {
		 			if(textField_searchTenNhanVien.equals("")) {
		 				return;
		 			}
		 			String[] parts = textField_searchTenNhanVien.split(" ");
			 		int soLuong = parts.length;
		 			String first_name = ""; 
			 		String	last_name = "";
			 		System.out.println(soLuong);
			 		for(int i = 0;i < soLuong-1; ) {
			 			first_name = first_name +  parts[i];
			 			i++;
			 			if(i < soLuong-1) {
			 				first_name = first_name +" ";
			 			}
			 			
			 		}
			 		
			 		last_name = last_name+ parts[soLuong-1];
					statement = connection.createStatement();
					String sqlstatement  ="select count(*) from staff where first_name = '"+first_name+"' and last_name = '"+last_name+"';" ;
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select staff_id, first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary from staff "
							+ "		where first_name = '"+first_name+"' and last_name = '"+last_name+"' "
							+ "			order by first_name;" ;
					
					System.out.println(sqlstatement);
					
					result = statement.executeQuery(sqlstatement);
					this.nhanVien = new String[row][8];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							nhanVien[i][0] = result.getString(1);
							nhanVien[i][1] = result.getString(2);
							nhanVien[i][2] = result.getString(3);
							nhanVien[i][3] = result.getString(4);
							nhanVien[i][4] = result.getString(5);
							nhanVien[i][5] = result.getString(6);
							nhanVien[i][6] = result.getString(7);
							nhanVien[i][7] = result.getString(8);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void timHoaDonNhanVienTao(String staff_id) throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  = "select count(*) from import_bill where staff_id = "+staff_id+";";
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select imbill_id ,imbill_date, state, netamount, imbill_money,actor_name "
							+ "	from import_bill ib,staff s,actor a "
							+ "		where ib.staff_id = s.staff_id and ib.actor_id = a.actor_id "
							+ "		and s.staff_id = "+staff_id+";";
					
					System.out.println(sqlstatement);
					
					result = statement.executeQuery(sqlstatement);
					this.hoaDon = new String[row][7];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							hoaDon[i][0] = result.getString(1);
							hoaDon[i][1] = result.getString(2);
							hoaDon[i][2] = result.getString(3);
							hoaDon[i][3] = result.getString(4);
							hoaDon[i][4] = result.getString(5);
							hoaDon[i][5] = result.getString(6);
							hoaDon[i][6] = staff_id;
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void timSanPhamTrongHoaDon(String imbill_id) throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  = "select count(*) from import_order_line where imbill_id = "+imbill_id+";";
					ResultSet result = statement.executeQuery(sqlstatement);
					
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select product_name,size,quantity from import_order_line iol, viewsp v"
							+ "	where iol.product_id = v.product_id and imbill_id = "+imbill_id+" ;";
					
					System.out.println(sqlstatement);
					
					result = statement.executeQuery(sqlstatement);
					this.sanPhamHoaDon = new String[row][3];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							sanPhamHoaDon[i][0] = result.getString(1);
							sanPhamHoaDon[i][1] = result.getString(2);
							sanPhamHoaDon[i][2] = result.getString(3);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void xemToanBoHoaDon() throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  = "select count(*) from import_bill;";
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select imbill_id ,imbill_date, state, netamount, imbill_money,actor_name,ib.staff_id "
							+ "	from import_bill ib,staff s,actor a "
							+ "		where ib.staff_id = s.staff_id and ib.actor_id = a.actor_id "
							+ "	order by imbill_date;";
					System.out.println(sqlstatement);
					
					result = statement.executeQuery(sqlstatement);
					this.hoaDon = new String[row][7];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							hoaDon[i][0] = result.getString(1);
							hoaDon[i][1] = result.getString(2);
							hoaDon[i][2] = result.getString(3);
							hoaDon[i][3] = result.getString(4);
							hoaDon[i][4] = result.getString(5);
							hoaDon[i][5] = result.getString(6);
							hoaDon[i][6] = result.getString(7);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void xemNhanVienTaoHoaDon(String staff_id) throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
	
					String sqlstatement  ="select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary " 
							+ "	from staff where staff_id = "+staff_id+";";					
					System.out.println(sqlstatement);
					
					ResultSet result = statement.executeQuery(sqlstatement);
					this.nhanVien = new String[1][7];
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							nhanVien[0][0] = result.getString(1);
							nhanVien[0][1] = result.getString(2);
							nhanVien[0][2] = result.getString(3);
							nhanVien[0][3] = result.getString(4);
							nhanVien[0][4] = result.getString(5);
							nhanVien[0][5] = result.getString(6);
							nhanVien[0][6] = result.getString(7);
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void xemToanBoThayDoiTrenKho() throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  = "select count(*) from change;";
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select c.staff_id,first_name ||' '|| last_name as full_name, date_change,product_name,size,actor_name "
							+ "from staff s, viewsp v,change c "
							+ "	where c.product_id = v.product_id and s.staff_id = c.staff_id; ";
										
					result = statement.executeQuery(sqlstatement);
					this.thayDoi = new String[row][7];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) {
							thayDoi[i][0] = result.getString(1);
							thayDoi[i][1] = result.getString(2);
							thayDoi[i][2] = result.getString(3);
							thayDoi[i][3] = result.getString(4);
							thayDoi[i][4] = result.getString(5);
							thayDoi[i][5] = result.getString(6);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void nhanVienThayDoiTrenKho(String staff_id) throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
					String sqlstatement  = "select count(*) from change where staff_id = "+staff_id+";";
					ResultSet result = statement.executeQuery(sqlstatement);
					result.next();
					int row = result.getInt(1);
					
					sqlstatement  ="select c.staff_id,first_name ||' '|| last_name as full_name, date_change,product_name,size,actor_name "
							+ "from staff s, viewsp v,change c "
							+ "	where c.product_id = v.product_id and s.staff_id = c.staff_id "
							+ " and c.staff_id = "+staff_id+";";
										
					result = statement.executeQuery(sqlstatement);
					this.thayDoi = new String[row][7];
					int i = 0;
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							thayDoi[i][0] = result.getString(1);
							thayDoi[i][1] = result.getString(2);
							thayDoi[i][2] = result.getString(3);
							thayDoi[i][3] = result.getString(4);
							thayDoi[i][4] = result.getString(5);
							thayDoi[i][5] = result.getString(6);
							i++;		
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
	public void xemThongTinNguoiThayDoiTrenKho(String staff_id) throws SQLException {
		 Statement statement = null;
		 	try {
					statement = connection.createStatement();
	
					String sqlstatement  ="select staff_id,first_name ||' '|| last_name as full_name,dob,gender,email,phone,address,salary " 
							+ "	from staff where staff_id = "+staff_id+";";					
					System.out.println(sqlstatement);
					
					ResultSet result = statement.executeQuery(sqlstatement);
					this.nhanVien = new String[1][7];
					System.out.println("Got results:");
					try{
						while (result.next()) { // process results one row at a time
							nhanVien[0][0] = result.getString(1);
							nhanVien[0][1] = result.getString(2);
							nhanVien[0][2] = result.getString(3);
							nhanVien[0][3] = result.getString(4);
							nhanVien[0][4] = result.getString(5);
							nhanVien[0][5] = result.getString(6);
							nhanVien[0][6] = result.getString(7);
						}
					} finally {
						try{result.close();}catch(Exception e) {e.printStackTrace(); }
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					 try{if (statement != null) statement.close();}catch(Exception e) {e.printStackTrace(); }
				} 
	 }
}
