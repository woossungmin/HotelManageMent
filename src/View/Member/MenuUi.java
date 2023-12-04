package View.Member;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Custom.Message;
import Custom.RoundedButton;
import DTO.MemberDto;
import Server.Controller;

import javax.swing.JButton;
import javax.swing.JTextField;

public class MenuUi {

	private JFrame frame;
	private int TotalPrice = 0;
	
	List<Map<String, String>> totalFoodList = new ArrayList<>();
	
	private JTextField roomNumTextField;

	public MenuUi(String em) {
		initialize(em);
	}

	private void initialize(String em) {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("");
		frame.setResizable(false);
		frame.setVisible(true);

		Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 3);

		JPanel BorderPanel = new JPanel();
		BorderPanel.setBounds(0, 0, 551, 403);
		frame.getContentPane().add(BorderPanel);
		BorderPanel.setBackground(new Color(255, 255, 255));
		BorderPanel.setLayout(null);

		JPanel BorderPanel2 = new JPanel();
		BorderPanel2.setBounds(12, 10, 467, 65);
		BorderPanel.add(BorderPanel2);
		BorderPanel2.setBorder(border);
		BorderPanel2.setBackground(new Color(255, 255, 255));
		BorderPanel2.setLayout(null);

		JPanel BorderPanel3 = new JPanel();
		BorderPanel3.setBounds(474, 10, 65, 65);
		BorderPanel.add(BorderPanel3);
		BorderPanel3.setBorder(border);
		BorderPanel3.setBackground(new Color(255, 255, 255));
		BorderPanel3.setLayout(null);

		JPanel BorderPanel4 = new JPanel();
		BorderPanel4.setBounds(12, 73, 527, 322);
		BorderPanel.add(BorderPanel4);
		BorderPanel4.setBorder(border);
		BorderPanel4.setBackground(new Color(255, 255, 255));
		BorderPanel4.setLayout(null);

		JLabel title = new JLabel("B");
		title.setForeground(new Color(64, 64, 64));
		title.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		title.setBounds(178, 15, 40, 37);
		BorderPanel2.add(title);

		JLabel title1 = new JLabel("A");
		title1.setForeground(new Color(64, 64, 64));
		title1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 39));
		title1.setBounds(296, 16, 40, 37);
		BorderPanel2.add(title1);

		JLabel title2 = new JLabel("ENIKE");
		title2.setForeground(new Color(64, 64, 64));
		title2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 33));
		title2.setBounds(203, 17, 99, 37);
		BorderPanel2.add(title2);

		JLabel BackButton = new JLabel("");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MainUi mainui = new MainUi(em);
			}
		});
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\log-in.png"));
		BackButton.setBounds(0, 0, 64, 64);
		BorderPanel3.add(BackButton);
		
		JLabel FoodText = new JLabel("식사 목록");
		FoodText.setForeground(Color.DARK_GRAY);
		FoodText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		FoodText.setBounds(14, 8, 94, 37);
		BorderPanel4.add(FoodText);

		RoundedButton CheckButton = new RoundedButton("결제");
		CheckButton.setBounds(420, 283, 95, 29);
		BorderPanel4.add(CheckButton);

		JLabel moneylabel = new JLabel("결제금액 : ");
		moneylabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		moneylabel.setForeground(new Color(255, 128, 128));
		moneylabel.setBounds(260, 283, 75, 25);
		BorderPanel4.add(moneylabel);
		moneylabel.setVisible(true);

		JLabel moneylabel_1 = new JLabel("");
		moneylabel_1.setForeground(new Color(255, 128, 128));
		moneylabel_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		moneylabel_1.setBounds(329, 283, 79, 25);
		BorderPanel4.add(moneylabel_1);
		moneylabel_1.setVisible(true);

		JLabel moneylabel_2 = new JLabel("원");
		moneylabel_2.setForeground(new Color(255, 128, 128));
		moneylabel_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		moneylabel_2.setBounds(374, 283, 30, 25);
		BorderPanel4.add(moneylabel_2);
		moneylabel_2.setVisible(true);
		
		// 테이블 생성
		JTable table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 모든 셀을 수정 불가능하게 설정
				return false;
			}
		};
		
		table.setFont(new Font("한컴산뜻돋움", Font.BOLD, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setBackground(new Color(255, 255, 255));
		table.setGridColor(new Color(64, 64, 64));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 테이블 마우스 클릭 이벤트
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        if (e.getClickCount() == 2) {
		            int row = table.getSelectedRow();
		            int col = table.getSelectedColumn();
		            String value = (String) table.getModel().getValueAt(row, 2);
		            String foodValue = (String) table.getModel().getValueAt(row, 1);

		            // 이미 존재하는지 확인
		            boolean foodExists = false;
		            
		            for (Map<String, String> food : totalFoodList) {
		                if (food.get("foodName").equals(foodValue)) {
		                    // 이미 있는 경우, count를 증가
		                    int count = Integer.parseInt(food.get("count"));
		                    count++;
		                    
		                    food.put("count", String.valueOf(count));
		                    foodExists = true;
		                    
		                    break;
		                }
		            }

		            // 없는 경우, 새로운 음식을 추가
		            if (!foodExists) {
		                Map<String, String> newFood = new HashMap<>();
		                newFood.put("foodName", foodValue);
		                newFood.put("foodPrice", value);
		                newFood.put("count", "1");
		                
		                totalFoodList.add(newFood);
		            } else {
		                // 이미 있는 경우, foodPrice 업데이트
		                for (Map<String, String> food : totalFoodList) {
		                    if (food.get("foodName").equals(foodValue)) {		                    	
		                        int price = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		                        int count = Integer.parseInt(food.get("count"));
		                        int totalPrice = price * count;		                        
		                        food.put("foodPrice", String.valueOf(totalPrice));
		                        
		                        break;
		                    }
		                }
		            }

		            // TotalPrice 계산
		            int price = Integer.parseInt(value.replaceAll("[^0-9]", ""));
		            TotalPrice += price;
		            
		            moneylabel_1.setText(String.valueOf(TotalPrice));
		        }
		    }
		});

		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 35));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"구분", "음식명", "가격", "음식설명"
			}
		));

		// 스크롤 패널에 테이블 추가
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(64, 64, 64));
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 3)); // 테두리 선 두께 설정
		scrollPane.setBounds(12, 50, 503, 212);
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    
//	    scrollPane.getVerticalScrollBar().setBackground(new Color(100, 100, 100)); // 수직 스크롤바 색상 설정
//	    scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
//
//	        @Override
//	        protected void configureScrollBarColors() {
//	            this.thumbColor = new Color(64, 64, 64);
//	        }
//
//	        @Override
//	        protected JButton createDecreaseButton(int orientation) {
//	            JButton button = super.createDecreaseButton(orientation);
//	            button.setBackground(new Color(100, 100, 100)); // 스크롤바 화살표 색상
//	            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100))); // 스크롤바 화살표 테두리 색상
//	            return button;
//	        }
//
//	        @Override
//	        protected JButton createIncreaseButton(int orientation) {
//	            JButton button = super.createIncreaseButton(orientation);
//	            button.setBackground(new Color(100, 100, 100)); // 스크롤바 화살표 색상
//	            button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100))); // 스크롤바 화살표 테두리 색상
//	            return button;
//	        }
//	    });

		BorderPanel4.add(scrollPane);

		table.getColumn("구분").setPreferredWidth(5);
		table.getColumn("음식명").setPreferredWidth(80);
		table.getColumn("가격").setPreferredWidth(30);
		table.getColumn("음식설명").setPreferredWidth(180);

        // 테이블 셀 가운데 정렬
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcm = table.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }
        
		// 테이블의 행 높이 설정
		table.setRowHeight(25);
		
		// 테이블 내부 선의 두께 설정
		table.setIntercellSpacing(new Dimension(0, 0));
		
		JLabel FoodText_1 = new JLabel("호  실 :");
		FoodText_1.setForeground(Color.DARK_GRAY);
		FoodText_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 17));
		FoodText_1.setBounds(14, 281, 66, 29);
		BorderPanel4.add(FoodText_1);
		
		roomNumTextField = new JTextField();
		roomNumTextField.setFont(new Font("한컴산뜻돋움", Font.BOLD, 17));
		roomNumTextField.setBounds(92, 284, 106, 27);
		BorderPanel4.add(roomNumTextField);
		
		roomNumTextField.setColumns(10);
		roomNumTextField.setBorder(border);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(Color.WHITE);
				((JComponent) c).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0, 0, 0)));
				return c;
			}
		});
		
		// 커스텀 헤더 렌더러 설정
		table.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
			
		  MemberDto memberDto = new MemberDto(); 
		  memberDto.setEmail(em);
		  try {
			    int number = 0;
			    List<String> rs = new Controller("localhost", 12347).SearchMenu();			    
			    
			    for (int i = 0; i < rs.size(); i+=3) {
			        String foodName = rs.get(i);
			        String priceStr = rs.get(i + 1);
			        String foodInfo = rs.get(i + 2);

			        // priceStr을 숫자로 파싱
			        int price = Integer.parseInt(priceStr);

			        // 숫자를 형식화하여 ','를 추가
			        DecimalFormat decimalFormat = new DecimalFormat("#,###");
			        String formattedPrice = decimalFormat.format(price);

			        // 나머지 코드는 그대로 사용
			        String num = Integer.toString(number);
			        String[] row = {num, foodName, formattedPrice + "원", foodInfo};
			        model.addRow(row);

			        number++;
			    }
			} 
		  catch (Exception e) {
			    e.printStackTrace();
			}
		  
		  CheckButton.addActionListener((ActionEvent e) -> {
			    try {
					Boolean rs = new Controller("localhost", 12347).InsertFood(em,roomNumTextField.getText(),totalFoodList);
					if(rs.equals(true)) {
						Message ms = new Message("음식 주문이 완료되었습니다!");						
					}
				} 
			    catch (ClassNotFoundException e1) {					
					e1.printStackTrace();
				} 
			    catch (IOException e1) {				
					e1.printStackTrace();
				}
			});		 
	}

	static class CustomHeaderRenderer implements TableCellRenderer {
		private TableCellRenderer defaultRenderer;

		public CustomHeaderRenderer() {
			this.defaultRenderer = new DefaultTableCellRenderer();
			((DefaultTableCellRenderer) defaultRenderer).setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JComponent renderer = (JComponent) defaultRenderer.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);

			if ("구분".equals(value) || "음식명".equals(value) || "가격".equals(value) || "음식설명".equals(value)) {
				renderer.setBackground(new Color(64, 64, 64));
				renderer.setForeground(new Color(255, 255, 255));
				renderer.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
				renderer.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));
			}

			return renderer;
		}
	}
}