package View.Member;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoundedButton;
import DTO.MemberDto;
import Server.Controller;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;


public class LoginUi {

   private JFrame frame;
   private JTextField email;
   private JPasswordField password;
   private JTextField name;

   public LoginUi() {
      initialize();
   }

   private void initialize() { 
      frame = new JFrame();
      frame.setBounds(380, 120, 565, 440);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setTitle("로그인");
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
      
      JLabel emaillabel = new JLabel("이  메  일  : ");
      emaillabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      emaillabel.setBounds(85, 61, 104, 30);
      BorderPanel.add(emaillabel);
      
      JLabel passwordlabel = new JLabel("비밀번호  : ");
      passwordlabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      passwordlabel.setBounds(85, 111, 104, 30);
      BorderPanel.add(passwordlabel);
      
      JLabel roonumberlabel = new JLabel("이      름   :");
      roonumberlabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      roonumberlabel.setBounds(86, 161, 104, 30);
      BorderPanel.add(roonumberlabel);
      
      RoundedButton LoginButton = new RoundedButton("로그인");
      LoginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String em = email.getText();
            String pw = password.getText();
            String na = name.getText();
            
            MemberDto memberDto = new MemberDto();            
            memberDto.setEmail(em);
            memberDto.setPw(pw);
            memberDto.setUserName(na);
            
            try {
               boolean state = new Controller("localhost",12347).LogIn(memberDto);
               if(state == true) {
                   MainUi mainui = new MainUi(em);
                   frame.dispose();
                   Message ms = new Message("로그인을 성공하였습니다.");
               }
               else {
                  Message ms = new Message("로그인을 실패하였습니다.");
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
      LoginButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
      LoginButton.setBounds(85, 216, 364, 39);
      BorderPanel.add(LoginButton);
      
      email = new JTextField();
      email.setBounds(220, 65, 225, 30);
      BorderPanel.add(email);
      email.setColumns(10);
      email.setBorder(border);
      
      password = new JPasswordField();
      password.setColumns(10);
      password.setBounds(220, 116, 225, 30);
      BorderPanel.add(password);
      password.setBorder(border);
      
      name = new JTextField();
      name.setColumns(10);
      name.setBounds(220, 166, 225, 30);
      BorderPanel.add(name);
      name.setBorder(border);
      
      JButton membership = new JButton("회원가입");
      membership.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		frame.dispose();
      		MembershipUi membershipui = new MembershipUi();
      	}
      });
      membership.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      membership.setBounds(105, 269, 117, 23);
      BorderPanel.add(membership);
      membership.setBorderPainted(false);
      membership.setContentAreaFilled(false);
      membership.setFocusPainted(false);
      
      JButton bar = new JButton("|");
      bar.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      bar.setBounds(230, 265, 50, 30);
      BorderPanel.add(bar);
      bar.setBorderPainted(false);
      bar.setContentAreaFilled(false);
      bar.setFocusPainted(false);
      
      JButton searchpassword = new JButton("비밀번호찾기");
      searchpassword.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
      searchpassword.setContentAreaFilled(false);
      searchpassword.setBorderPainted(false);
      searchpassword.setFocusPainted(false);
      searchpassword.setBounds(288, 268, 152, 23);
      BorderPanel.add(searchpassword);
   }
}