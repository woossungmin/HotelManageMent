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

public class CheckOutStateUi {

	private JFrame frame;

	public CheckOutStateUi(String RoomNum, JFrame CheckOutFrame) {
		initialize(RoomNum,CheckOutFrame);
	}

	private void initialize(String RoomNum,JFrame CheckOutFrame) {
		frame = new JFrame();		
		frame.setBounds(500, 230, 300, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	    frame.setResizable(false);
	    frame.setVisible(true);
		
		Border border =  BorderFactory.createLineBorder(new Color(64,64,64),3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 286, 113);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255));
		
		JPanel BorderPanel = new JPanel();
		BorderPanel.setBounds(12, 10, 262, 93);
		panel.add(BorderPanel);
		BorderPanel.setBackground(new Color(255,255,255));
		BorderPanel.setBorder(border);
		BorderPanel.setLayout(null);
        
        RoundedButton InButton = new RoundedButton("퇴실");

        InButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 try {
 	            	boolean state = new Controller("localhost",12347).CheckOut(RoomNum);
 	            	
 	               if(state ==true) {
 	                   frame.dispose();
 	                   CheckOutFrame.dispose();
 	                   
 	                   MainUi mainui = new MainUi("");
 	                   Message ms = new Message("퇴실을 성공하였습니다.");
 	                   
 	               }
 	               else {
 	                  Message ms = new Message("퇴실을 실패하였습니다.");
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
        
        InButton.setBounds(34, 60, 81, 23);
        BorderPanel.add(InButton);
        
        RoundedButton CancleButton = new RoundedButton("취소");
        CancleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();        		
        	}
        });
        
        CancleButton.setBounds(151, 60, 81, 23);
        BorderPanel.add(CancleButton);
        
        JLabel RoomLabel = new JLabel("퇴실 하시겠습니까?");
        RoomLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
        RoomLabel.setBounds(68, 13, 136, 40);
        BorderPanel.add(RoomLabel);
	}
}
