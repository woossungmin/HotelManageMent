package View.Member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoundedButton;
import DTO.MemberDto;
import Server.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoUi {

	private JFrame frame;

	public InfoUi(String em) {
		initialize(em);
	}

	private void initialize(String em) {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("내 정보 확인");
	    frame.setResizable(false);
	    frame.setVisible(true);
		
	    Border border =  BorderFactory.createLineBorder(new Color(0,0,0),3);
		
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
		LabelPanel.setBackground(new Color(64,64,64));
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
				MainUi mainui = new MainUi(em);
				frame.dispose();
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\delete-button.png"));
		BackButton.setBounds(491, 10, 24, 24);
		BorderPanel.add(BackButton);
	
		JLabel eamillabel = new JLabel("이  메  일  : ");
		eamillabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		eamillabel.setBounds(85, 41, 104, 30);
		BorderPanel.add(eamillabel);
		
		JLabel passwordlabel = new JLabel("비밀번호   : ");
		passwordlabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		passwordlabel.setBounds(85, 91, 104, 30);
		BorderPanel.add(passwordlabel);
		
		JLabel phonelabel = new JLabel("전화번호   :");
		phonelabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		phonelabel.setBounds(85, 141, 104, 30);
		BorderPanel.add(phonelabel);
		
		JLabel namelabel = new JLabel("이       름   :");
		namelabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		namelabel.setBounds(85, 191, 104, 30);
		BorderPanel.add(namelabel);
		
		JTextField password = new JTextField();
		password.setFont(new Font("굴림", Font.BOLD, 12));
		password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				password.setFocusable(true);
				password.setBackground(new Color(255,255,255));
				password.setText("");
			}
		});
		
		password.setColumns(10);
		password.setBounds(220, 96, 225, 30);
		BorderPanel.add(password);
		password.setBorder(border);
		password.setFocusable(false);
		password.setBackground(new Color(240,240,240));
		
		JTextField email = new JTextField();
		email.setFont(new Font("굴림", Font.BOLD, 12));
		
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				email.setFocusable(true);
				email.setBackground(new Color(255,255,255));
				email.setText("");
			}
		});
		
		email.setBounds(220, 45, 225, 30);
		BorderPanel.add(email);
		email.setColumns(10);
		email.setBorder(border);
		email.setFocusable(false);
		email.setBackground(new Color(240,240,240));
		
		JTextField phone = new JTextField();
		phone.setFont(new Font("굴림", Font.BOLD, 12));
		
		phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				phone.setFocusable(true);
				phone.setBackground(new Color(255,255,255));
				phone.setText("");
			}
		});
		
		phone.setColumns(10);
		phone.setBounds(220, 146, 225, 30);
		BorderPanel.add(phone);
		phone.setBorder(border);
		phone.setFocusable(false);
		phone.setBackground(new Color(240,240,240));
		
		JTextField name = new JTextField();
		name.setFont(new Font("굴림", Font.BOLD, 12));
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				name.setFocusable(true);
				name.setBackground(new Color(255,255,255));
				name.setText("");
			}
		});
		
		name.setColumns(10);
		name.setBounds(219, 196, 225, 30);
		BorderPanel.add(name);
		name.setBorder(border);
		name.setFocusable(false);
		name.setBackground(new Color(240,240,240));
		
		RoundedButton UpdateButton = new RoundedButton("개인 정보 수정");
		UpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDto memberDto = new MemberDto();
				memberDto.setEmail(email.getText());
				memberDto.setPhone(phone.getText());
				memberDto.setPw(password.getText());
				memberDto.setUserName(name.getText());
				
				try {
	            	boolean state = new Controller("localhost",12347).MemberUpdate(memberDto,em);
	               if(state == true) {
	                   frame.dispose();
	                   
	                   MainUi mainui = new MainUi(memberDto.getEmail());
	                   Message ms = new Message("정보수정을 성공하였습니다.");

	               }
	               else {
	                  Message ms = new Message("정보수정을 실패하였습니다.");
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
		
		UpdateButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		UpdateButton.setBounds(85, 248, 364, 39);
		BorderPanel.add(UpdateButton);
		
		MemberDto memberDto = new MemberDto();
		memberDto.setEmail(em);
		
		  try {
              MemberDto rs = new Controller("localhost",12347).SearchInfo(memberDto);
              
              System.out.println(rs.getEmail());
           	  email.setText(rs.getEmail());
           	  phone.setText(rs.getPhone());
           	  name.setText(rs.getUserName());
           	  password.setText(rs.getPw());
           } 
		  catch (IOException e1) {
              //e1.printStackTrace();
           } 
		  catch (ClassNotFoundException ex) {
                   //throw new RuntimeException(ex);
           }
		
	}

}
