package view;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.NhanVienModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class NhanVienView extends JFrame {

	
	private MenuChuView menuChuView;
	private JPanel contentPane;
	private JTextField textField_searchTenNhanVien;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private NhanVienModel nhanVienModel;
	

	/**
	 * Launch the application.
	 */
public NhanVienView(MenuChuView menuChuView) throws SQLException {
		
		this.menuChuView = menuChuView;
	 	
		this.nhanVienModel = new NhanVienModel();
		
		this.init();
		setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		
		try {
			nhanVienModel = new NhanVienModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 605);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Close");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("About");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("About me");
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setToolTipText("kk");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_searchTenNhanVien = new JTextField();
		textField_searchTenNhanVien.setBounds(10, 11, 120, 30);
		contentPane.add(textField_searchTenNhanVien);
		textField_searchTenNhanVien.setColumns(10);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timNhanVienTheoTen();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(140, 11, 89, 30);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("All");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xemToanBoNhanVien();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(485, 11, 89, 30);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setBounds(10, 52, 564, 122);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					timHoaDonNhanVienTao();
					nhanVienThayDoiTrenKho();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"T\u00EAn", "Ng\u00E0y sinh", "Gender", "Email", "Phone", "Address", "Ti\u1EC1n c\u00F4ng"
			}
		));
		table.getColumnModel().getColumn(0).setMinWidth(75);
		table.getColumnModel().getColumn(0).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(45);
		table.getColumnModel().getColumn(2).setMinWidth(45);
		table.getColumnModel().getColumn(2).setMaxWidth(45);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setToolTipText("");
		scrollPane_1.setBounds(10, 215, 365, 122);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					timSanPhamTrongHoaDon();
					xemNhanVienTaoHoaDon();
					nhanVienThayDoiTrenKho_clickOnTable_1();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Ngày tạo", "Trạng thái", "Số lượng", "Tổng tiền", "Tên nhà SX"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setToolTipText("");
		scrollPane_1_1.setBounds(385, 215, 189, 122);
		contentPane.add(scrollPane_1_1);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Tên sản phẩm", "size", "số lượng"
			}
		));
		scrollPane_1_1.setViewportView(table_2);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xemToanBoHoaDon();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAll.setBounds(286, 185, 89, 30);
		contentPane.add(btnAll);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setToolTipText("");
		scrollPane_2.setBounds(10, 378, 564, 122);
		contentPane.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					xemThongTinNguoiThayDoiTrenKho();
					timHoaDonNhanVienTao_clickOnTable_3();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Tên nhân viên", "Ngày", "Tên Sản phẩm", "size", "Tên nhà sản xuất"
			}
		));
		scrollPane_2.setViewportView(table_3);
		
		JButton btnAll_1 = new JButton("All");
		btnAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xemToanBoThayDoiTrenKho();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAll_1.setBounds(485, 348, 89, 30);
		contentPane.add(btnAll_1);
		
		JButton btnAll_1_1 = new JButton("QUIT");
		btnAll_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				menuChuView.setVisible(true);
			}
		});
		btnAll_1_1.setBounds(485, 511, 89, 30);
		contentPane.add(btnAll_1_1);
	}
	public void xemToanBoNhanVien() throws SQLException {
		// TODO Auto-generated method stub
		this.nhanVienModel.xemToanBoNhanVien();
		table.setModel(new DefaultTableModel(
				this.nhanVienModel.getNhanVien(),
				new String[] {
						"ID","Tên", "Ngày sinh", "Gender", "Email", "Phone", "Address", "Tiền công"
				}
			));
		table.removeColumn(table.getColumnModel().getColumn(0));
		}
		
	public void timNhanVienTheoTen() throws SQLException {
		String textField_searchTenNhanVien = this.textField_searchTenNhanVien.getText();
		this.nhanVienModel.timNhanVienTheoTen(textField_searchTenNhanVien);
		table.setModel(new DefaultTableModel(
				this.nhanVienModel.getNhanVien(),
				new String[] {
						"ID","Tên", "Ngày sinh", "Gender", "Email", "Phone", "Address", "Tiền công"
				}
			));
		table.removeColumn(table.getColumnModel().getColumn(0));
	}
	public void timHoaDonNhanVienTao() throws SQLException {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		this.nhanVienModel.timHoaDonNhanVienTao(model_table.getValueAt(i_row, 0)+"");
		table_1.setModel(new DefaultTableModel(
				this.nhanVienModel.getHoaDon(),
				new String[] {
						"ID","Ngày tạo", "Trạng thái", "Số lượng", "Tổng tiền", "Tên nhà SX","staff_id"
				}
			));
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		table_1.removeColumn(table_1.getColumnModel().getColumn(5));
	}
	public void timSanPhamTrongHoaDon() throws SQLException {
		DefaultTableModel model_table_1= (DefaultTableModel) table_1.getModel();
		int i_row = table_1.getSelectedRow();
		this.nhanVienModel.timSanPhamTrongHoaDon(model_table_1.getValueAt(i_row, 0)+"");
		table_2.setModel(new DefaultTableModel(
				this.nhanVienModel.getSanPhamHoaDon(),
				new String[] {
						"Tên sản phẩm", "size", "số lượng"
				}
			));
	}
	public void xemToanBoHoaDon() throws SQLException {
		this.nhanVienModel.xemToanBoHoaDon();
		table_1.setModel(new DefaultTableModel(
				this.nhanVienModel.getHoaDon(),
				new String[] {
						"ID","Ngày tạo", "Trạng thái", "Số lượng", "Tổng tiền", "Tên nhà SX","staff_id"
				}
			));
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		table_1.removeColumn(table_1.getColumnModel().getColumn(5));
	}
	public void xemNhanVienTaoHoaDon() throws SQLException {
		// TODO Auto-generated method stub
		DefaultTableModel model_table_1= (DefaultTableModel) table_1.getModel();
		int i_row = table_1.getSelectedRow();
		this.nhanVienModel.xemNhanVienTaoHoaDon(model_table_1.getValueAt(i_row, 6)+"");
		table.setModel(new DefaultTableModel(
				this.nhanVienModel.getNhanVien(),
				new String[] {
						"ID","Tên", "Ngày sinh", "Gender", "Email", "Phone", "Address", "Tiền công"
				}
			));
		table.removeColumn(table.getColumnModel().getColumn(0));
		}
	public void xemToanBoThayDoiTrenKho() throws SQLException {
		// TODO Auto-generated method stub
		this.nhanVienModel.xemToanBoThayDoiTrenKho();
		table_3.setModel(new DefaultTableModel(
				this.nhanVienModel.getThayDoi(),
				new String[] {
						"staff_id","Tên nhân viên", "Ngày", "Tên Sản phẩm", "size", "Tên nhà sản xuất"
				}
			));
		table_3.removeColumn(table_3.getColumnModel().getColumn(0));
		}
	public void nhanVienThayDoiTrenKho() throws SQLException {
		// TODO Auto-generated method stub
		DefaultTableModel model_table= (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		this.nhanVienModel.nhanVienThayDoiTrenKho(model_table.getValueAt(i_row, 0)+"");
		table_3.setModel(new DefaultTableModel(
				this.nhanVienModel.getThayDoi(),
				new String[] {
						"staff_id","Tên nhân viên", "Ngày", "Tên Sản phẩm", "size", "Tên nhà sản xuất"
				}
			));
		table_3.removeColumn(table_3.getColumnModel().getColumn(0));
		}
	public void xemThongTinNguoiThayDoiTrenKho() throws SQLException {
		// TODO Auto-generated method stub
		DefaultTableModel model_table_3= (DefaultTableModel) table_3.getModel();
		int i_row = table_3.getSelectedRow();
		this.nhanVienModel.xemThongTinNguoiThayDoiTrenKho(model_table_3.getValueAt(i_row, 0)+"");
		table.setModel(new DefaultTableModel(
				this.nhanVienModel.getNhanVien(),
				new String[] {
						"ID","Tên", "Ngày sinh", "Gender", "Email", "Phone", "Address", "Tiền công"
				}
			));
		table.removeColumn(table.getColumnModel().getColumn(0));
		}
	public void nhanVienThayDoiTrenKho_clickOnTable_1() throws SQLException {
		// TODO Auto-generated method stub
		DefaultTableModel model_table= (DefaultTableModel) table.getModel();
		this.nhanVienModel.nhanVienThayDoiTrenKho(model_table.getValueAt(0, 0)+"");
		table_3.setModel(new DefaultTableModel(
				this.nhanVienModel.getThayDoi(),
				new String[] {
						"staff_id","Tên nhân viên", "Ngày", "Tên Sản phẩm", "size", "Tên nhà sản xuất"
				}
			));
		table_3.removeColumn(table_3.getColumnModel().getColumn(0));
		}
	public void timHoaDonNhanVienTao_clickOnTable_3() throws SQLException {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		this.nhanVienModel.timHoaDonNhanVienTao(model_table.getValueAt(0, 0)+"");
		table_1.setModel(new DefaultTableModel(
				this.nhanVienModel.getHoaDon(),
				new String[] {
						"ID","Ngày tạo", "Trạng thái", "Số lượng", "Tổng tiền", "Tên nhà SX","staff_id"
				}
			));
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		table_1.removeColumn(table_1.getColumnModel().getColumn(5));
	}
}
