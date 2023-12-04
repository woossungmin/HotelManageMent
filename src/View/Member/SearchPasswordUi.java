package View.Member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoundedButton;
import DTO.MemberDto;
import Server.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchPasswordUi {

	private JFrame frame;

	public SearchPasswordUi() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("회원가입");
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
		
		JLabel emailtext = new JLabel("이  메  일  : ");
		emailtext.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		emailtext.setBounds(85, 44, 104, 30);
		BorderPanel.add(emailtext);
		
		JLabel phonetext = new JLabel("전화번호  : ");
		phonetext.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		phonetext.setBounds(85, 94, 104, 30);
		BorderPanel.add(phonetext);
		
		JLabel nametext = new JLabel("이      름   :");
		nametext.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		nametext.setBounds(86, 144, 104, 30);
		BorderPanel.add(nametext);
		
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
		
		JLabel message = new JLabel("※ 개인 정보를 모두 입력해주세요 ※");
		message.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		message.setBounds(118, 195, 320, 37);
		BorderPanel.add(message);
		message.setForeground(new Color(255,128,128));
		message.setVisible(true);
		
		String pass = ""; // 비밀번호 찾기 후 담을 변수
		JLabel passwordlabel = new JLabel("");
		passwordlabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		passwordlabel.setBounds(130, 195, 320, 37);
		BorderPanel.add(passwordlabel);
		
		passwordlabel.setForeground(new Color(64,64,64));
		passwordlabel.setVisible(false);
		passwordlabel.setText("비밀번호 : " + pass); //비밀번호 setText
		
		JTextField email = new JTextField();
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				message.setVisible(true);
				passwordlabel.setVisible(false);
			}
		});
		
		email.setBounds(220, 48, 225, 30);
		BorderPanel.add(email);
		email.setColumns(10);
		email.setBorder(border);
		
		JTextField phone = new JTextField();
		phone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				message.setVisible(true);
				passwordlabel.setVisible(false);
			}
		});
		
		phone.setColumns(10);
		phone.setBounds(220, 99, 225, 30);
		BorderPanel.add(phone);
		phone.setBorder(border);
		
		JTextField name = new JTextField();
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				message.setVisible(true);
				passwordlabel.setVisible(false);
			}
		});
		
		name.setColumns(10);
		name.setBounds(220, 149, 225, 30);
		BorderPanel.add(name);
		name.setBorder(border);		
		
		RoundedButton SearchPassword = new RoundedButton("비밀번호 찾기");
		SearchPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setVisible(false);
				passwordlabel.setVisible(true);
				
				String em = email.getText();
				String ph = phone.getText();
				String na = name.getText();
				
	            MemberDto memberDto = new MemberDto();
	            memberDto.setEmail(em);
	            memberDto.setPhone(ph);
	            memberDto.setUserName(na);
	            
	            try {
	               String pw = new Controller("localhost",12347).SearchPw(memberDto);
	               if(!pw.equals("false")) {
	            	   passwordlabel.setText("회원의 비밀번호는" + "  " + pw + "  " + "입니다!");
	               }
	               else {
	                  passwordlabel.setText("회원정보가 올바르지 않습니다!");
	                  email.setText("");
	                  phone.setText("");
	                  name.setText("");
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
		
		SearchPassword.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		SearchPassword.setBounds(85, 250, 364, 39);
		BorderPanel.add(SearchPassword);
		
		JLabel BackButton = new JLabel("");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				LoginUi loginui = new LoginUi();
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\delete-button.png"));
		BackButton.setBounds(491, 10, 24, 24);
		BorderPanel.add(BackButton);		
	}
}
