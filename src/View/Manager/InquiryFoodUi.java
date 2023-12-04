package View.Manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;
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
import DTO.FoodRequestDto;
import DTO.MemberDto;
import Server.Controller;
import View.Manager.MemberManagementUi.CustomHeaderRenderer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InquiryFoodUi {

	private JFrame frame;
    FoodRequestDto foodRequestDto = new FoodRequestDto();

	public InquiryFoodUi() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("식사 요청 조회");
	    frame.setResizable(false);
	    frame.setVisible(true);	      
	    
	    Border border =  BorderFactory.createLineBorder(new Color(118,113,113),3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 551, 403);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(255,255,255));
		panel.setLayout(null);
		
		JPanel BorderPanel = new JPanel();
		BorderPanel.setBounds(12, 77, 527, 316);
		panel.add(BorderPanel);
		BorderPanel.setBackground(new Color(255,255,255));
		BorderPanel.setBorder(border);
		BorderPanel.setLayout(null);
		
		JPanel LabelPanel = new JPanel();
		LabelPanel.setBounds(12, 10, 527, 69);
		panel.add(LabelPanel);
		LabelPanel.setBackground(new Color(175,171,171));
		LabelPanel.setBorder(border);
		LabelPanel.setLayout(null);
		
		JLabel title = new JLabel("B");
		title.setForeground(new Color(255, 255, 255));
		title.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		title.setBounds(190, 15, 40, 37);
		LabelPanel.add(title);
		
		JLabel title1 = new JLabel("A");
		title1.setForeground(new Color(255, 255, 255));
		title1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 39));
		title1.setBounds(308, 16, 40, 37);
		LabelPanel.add(title1);
		
		JLabel title2 = new JLabel("ENIKE");
		title2.setForeground(new Color(255, 255, 255));
		title2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 33));
		title2.setBounds(215, 17, 99, 37);
		LabelPanel.add(title2);
		
		JLabel BackButton = new JLabel("");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MainUi mainui = new MainUi();
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\delete-button2.png"));
		BackButton.setBounds(491, 10, 24, 24);
		BorderPanel.add(BackButton);
		
		JLabel text = new JLabel("식사 요청 조회");
        text.setForeground(Color.DARK_GRAY);
        text.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
        text.setBounds(12, 10, 104, 30);
        BorderPanel.add(text);
        
        RoundedButton CancleButton = new RoundedButton("취소");        
        CancleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
 	            	boolean state = new Controller("localhost",12347).Canclefood(foodRequestDto);
 	            	
 	               if(state == true) {
 	            	   
 	            	   frame.dispose();
 	                   InquiryFoodUi inquiryfoodui = new InquiryFoodUi();
 	            	   Message ms = new Message("주문 취소를 완료하였습니다.");
 	                   
 	               }

 	            } 
        		catch (IOException e1) { 	             
 	               //e1.printStackTrace();
 	            } 
        		catch (ClassNotFoundException ex) {
 	                    //throw new RuntimeException(ex);
 	            }
        	}
        });
        
        CancleButton.setBounds(12, 283, 70, 23);
        BorderPanel.add(CancleButton);
        CancleButton.setBackgroundColor(new Color(175,171,171));
        
        RoundedButton OkButton = new RoundedButton("완료");
        OkButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
 	            	boolean state = new Controller("localhost",12347).Okfood(foodRequestDto); 	
 	            	
 	               if(state == true) { 	            	   
 	            	   frame.dispose();
 	            	   
 	                   InquiryFoodUi inquiryfoodui = new InquiryFoodUi();
 	                   
 	            	   Message ms = new Message("주문을 완료하였습니다."); 	                   
 	               }

 	            } catch (IOException e1) { 	              
 	               //e1.printStackTrace();
 	            } catch (ClassNotFoundException ex) {
 	                    //throw new RuntimeException(ex);
 	             }
        	}
        });
        
        OkButton.setBounds(445, 283, 70, 23);
        BorderPanel.add(OkButton);
        OkButton.setBackgroundColor(new Color(175,171,171));
        
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
        table.setGridColor(new Color(118, 113, 113));
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 테이블 마우스 클릭 이벤트
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) { // 더블 클릭 확인
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    
                    String OrderNum = (String) table.getModel().getValueAt(row, 1);
                    String RoomNum = (String) table.getModel().getValueAt(row, 2);
		            String FoodName = (String) table.getModel().getValueAt(row, 3);
		            
		            foodRequestDto.setOrderNum(OrderNum);
		            foodRequestDto.setRoomNum(RoomNum);
		            foodRequestDto.setFoodName(FoodName);
                }
            }
        });
        
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 35));
        
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"구분","주문번호","호실","음식명", "가격", "수량", "현황"
        	}
        ));

        // 스크롤 패널에 테이블 추가
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(175, 171, 171));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(118, 113, 113), 3)); // 테두리 선 두께 설정
        scrollPane.setBounds(12, 50, 503, 216);
        scrollPane.getVerticalScrollBar().setBackground(new Color(195, 191, 191)); // 수직 스크롤바 색상 설정
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(175,171,171);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(new Color(195, 191, 191)); // 스크롤바 화살표 색상
                button.setBorder(BorderFactory.createLineBorder(new Color(195, 191, 191))); // 스크롤바 화살표 테두리 색상
                
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(new Color(195, 191, 191)); // 스크롤바 화살표 색상
                button.setBorder(BorderFactory.createLineBorder(new Color(195,191,191))); // 스크롤바 화살표 테두리 색상
                
                return button;
            }
        });
        
        BorderPanel.add(scrollPane);
        
        table.getColumn("구분").setPreferredWidth(1);
        table.getColumn("주문번호").setPreferredWidth(80);
        table.getColumn("호실").setPreferredWidth(1);
        table.getColumn("음식명").setPreferredWidth(70);
        table.getColumn("가격").setPreferredWidth(40);
        table.getColumn("수량").setPreferredWidth(15);
        table.getColumn("현황").setPreferredWidth(15);
        
		  try {
			    int number = 0;
			    List<String> rs = new Controller("localhost", 12347).SearchFood();
			    
			    
			    for (int k = 0; k < rs.size(); k+=6) {
			    	String roomNum = rs.get(k);
			        String foodName = rs.get(k + 1);
			        String priceStr = rs.get(k + 2);
			        String counts = rs.get(k + 3);
			        String state = rs.get(k + 4);
			        String orderNum = rs.get(k + 5);
			        
			        // priceStr을 숫자로 파싱
			        int price = Integer.parseInt(priceStr);

			        // 숫자를 형식화하여 ','를 추가
			        DecimalFormat decimalFormat = new DecimalFormat("#,###");
			        String formattedPrice = decimalFormat.format(price);

			        // 나머지 코드는 그대로 사용
			        String num = Integer.toString(number);
			        if(state.equals("0")) {
			        	state = "-";
			        }
			        
			        String[] row = {num,orderNum, roomNum, foodName, formattedPrice + "원",counts,state};
			        model.addRow(row);

			        number++;
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}

        // 테이블 셀 가운데 정렬
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
        
        TableColumnModel tcm = table.getColumnModel();
        for (int i = 0; i < tcm.getColumnCount(); i++) {
            tcm.getColumn(i).setCellRenderer(dtcr);
        }

        // 테이블의 행 높이 설정
        table.setRowHeight(20);
        
        // 테이블 내부 선의 두께 설정
        table.setIntercellSpacing(new Dimension(0, 0));
        
        // 커스텀 헤더 렌더러 설정
        table.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
	}
	
	 static class CustomHeaderRenderer implements TableCellRenderer {
	        private TableCellRenderer defaultRenderer;

	        public CustomHeaderRenderer() {
	            this.defaultRenderer = new DefaultTableCellRenderer();
	            ((DefaultTableCellRenderer) defaultRenderer).setHorizontalAlignment(SwingConstants.CENTER);
	        }

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            JComponent renderer = (JComponent) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	            if ("구분".equals(value) ||"주문번호".equals(value) || "호실".equals(value) || "음식명".equals(value) || "가격".equals(value) || "수량".equals(value) || "현황".equals(value)) {
	                renderer.setBackground(new Color(175, 171, 171));
	                renderer.setForeground(new Color(255, 255, 255));
	                renderer.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
	                renderer.setBorder(BorderFactory.createLineBorder(new Color(118, 113, 113), 1));
	            }

	            return renderer;
	        }
	    }

}
