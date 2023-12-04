package View.Member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoundedButton;
import DTO.MemberDto;
import Server.Controller;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MembershipUi {

	private JFrame frame;

	public MembershipUi() {
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
		
		JLabel nametext = new JLabel("이       름   :");
		nametext.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		nametext.setBounds(85, 191, 104, 30);
		BorderPanel.add(nametext);
		
		
		JTextField password = new JTextField();
		password.setColumns(10);
		password.setBounds(220, 96, 225, 30);
		BorderPanel.add(password);
		password.setBorder(border);
		
		JTextField email = new JTextField();
		email.setBounds(220, 45, 225, 30);
		BorderPanel.add(email);
		email.setColumns(10);
		email.setBorder(border);
		
		JTextField phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(220, 146, 225, 30);
		BorderPanel.add(phone);
		phone.setBorder(border);
		
		JTextField name = new JTextField();
		name.setColumns(10);
		name.setBounds(219, 196, 225, 30);
		BorderPanel.add(name);
		name.setBorder(border);
		
		RoundedButton MembershipButton = new RoundedButton("회원가입");
		MembershipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String emails = email.getText();
				String phones = phone.getText();
				String names = name.getText();
				String pw = password.getText();
				
	            MemberDto memberDto = new MemberDto();
	            memberDto.setEmail(emails);
	            memberDto.setPhone(phones);
	            memberDto.setPw(pw);
	            memberDto.setUserName(names);

	            try {
	            	boolean state = new Controller("localhost",12347).Signup(memberDto);
	               if(state == true) {
	                   frame.dispose();
	                   
	                   LoginUi mainui = new LoginUi();
	                   Message ms = new Message("회원가입을 성공하였습니다.");

	               }
	               else {
	                  Message ms = new Message("회원가입을 실패하였습니다.");
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
		
		MembershipButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		MembershipButton.setBounds(85, 250, 364, 39);
		BorderPanel.add(MembershipButton);
				
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
