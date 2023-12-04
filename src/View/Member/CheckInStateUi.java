package View.Member;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoundedButton;
import DTO.CheckInDto;
import Server.Controller;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class CheckInStateUi {

	private JFrame frame;

	public CheckInStateUi(CheckInDto checkinDto, JFrame CheckInUiFrame ) {
		initialize(checkinDto,CheckInUiFrame);
	}

	private void initialize(CheckInDto checkindto, JFrame CheckInUiFrame) {
        frame = new JFrame();
        frame.setBounds(480, 130, 400, 335);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 3);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 386, 298);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        frame.setTitle("");
        frame.setResizable(false);
        frame.setVisible(true);
        panel.setBackground(new Color(255,255,255));
        
        JPanel BorderPanel = new JPanel();
        BorderPanel.setBounds(12, 10, 362, 278);
        panel.add(BorderPanel);
        BorderPanel.setLayout(null);
        BorderPanel.setBorder(border);
        BorderPanel.setBackground(new Color(255,255,255));
        
        RoundedButton InButton = new RoundedButton("입실");
        InButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
 	            	boolean state = new Controller("localhost",12347).CheckIn(checkindto);
 	            	
 	               if(state == true) {
 	                   frame.dispose();
 	                   CheckInUiFrame.dispose();
 	                   
 	                   MainUi mainui = new MainUi("");
 	                   Message ms = new Message("입실을 성공하였습니다.");

 	               }
 	               else {
 	                  Message ms = new Message("입실을 실패하였습니다.");
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
        
        InButton.setBounds(68, 245, 81, 23);        
        BorderPanel.add(InButton);
        
        RoundedButton CancleButton = new RoundedButton("취소");
        CancleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();        		
        	}
        });
        
        CancleButton.setBounds(210, 245, 81, 23);
        BorderPanel.add(CancleButton);
        
        JLabel EmailLabel = new JLabel("E - mail : ");
        EmailLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        EmailLabel.setBounds(20, 34, 81, 23);
        BorderPanel.add(EmailLabel);
        
        JLabel EmailText = new JLabel("");
        EmailText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        EmailText.setBounds(105, 33, 245, 23);
        BorderPanel.add(EmailText);
        EmailText.setForeground(new Color(120,120,120));
        EmailText.setText(checkindto.getEmail());
        
        JLabel RoomLabel = new JLabel("호      실  : ");
        RoomLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        RoomLabel.setBounds(20, 74, 81, 23);
        BorderPanel.add(RoomLabel);
        
        JLabel RoomText = new JLabel("");
        RoomText.setForeground(new Color(120, 120, 120));
        RoomText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        RoomText.setBounds(105, 73, 245, 23);
        BorderPanel.add(RoomText);
        RoomText.setText(checkindto.getRoomNum());
        
        JLabel InTimeLabel = new JLabel("입실시간 : ");
        InTimeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        InTimeLabel.setBounds(20, 114, 81, 23);
        BorderPanel.add(InTimeLabel);
        
        JLabel InTimeText = new JLabel("");
        InTimeText.setForeground(new Color(120, 120, 120));
        InTimeText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        InTimeText.setBounds(105, 113, 245, 23);
        BorderPanel.add(InTimeText);
        InTimeText.setText(checkindto.getIntime());
        
        JLabel OutTimeLabel = new JLabel("퇴실시간 : ");
        OutTimeLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        OutTimeLabel.setBounds(20, 154, 81, 23);
        BorderPanel.add(OutTimeLabel);
        
        JLabel OutTimeText = new JLabel("");
        OutTimeText.setForeground(new Color(120, 120, 120));
        OutTimeText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        OutTimeText.setBounds(105, 153, 245, 23);
        BorderPanel.add(OutTimeText);
        OutTimeText.setText(checkindto.getOutTime());
        
        JLabel PriceLabel = new JLabel("가      격  : ");
        PriceLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        PriceLabel.setBounds(20, 194, 81, 23);
        BorderPanel.add(PriceLabel);

        
        JLabel PriceText = new JLabel("");
        PriceText.setForeground(new Color(120, 120, 120));
        PriceText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 18));
        PriceText.setBounds(105, 193, 245, 23);
        BorderPanel.add(PriceText);
        PriceText.setText("");
        
        try {
            String priceString = new Controller("localhost",12347).SearchPrice(checkindto.getRoomNum());
            
            if(!priceString.equals(null)) {
                double price = Double.parseDouble(priceString);

                NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                String formattedPrice = numberFormat.format(price);
                PriceText.setText(formattedPrice + "원"); // 수정된 부분
            }
         } 
        catch (IOException e1) {
            //e1.printStackTrace();
         } 
        catch (ClassNotFoundException ex) {
                 //throw new RuntimeException(ex);
             }

	}
}
