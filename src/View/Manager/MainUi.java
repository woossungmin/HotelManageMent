package View.Manager;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Server.Service;
import View.Member.MemberChatUi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MainUi {

	private JFrame frame;

	public MainUi() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("");
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
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\delete-button2.png"));
		BackButton.setBounds(491, 10, 24, 24);
		BorderPanel.add(BackButton);
		
		JLabel ChatButton = new JLabel("New label");
		ChatButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ManagerChatUi chat = new  ManagerChatUi();
			}
		});
		
		ChatButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\chat1.png"));
		ChatButton.setBounds(57, 107, 64, 64);
		BorderPanel.add(ChatButton);
		
		JLabel cicle2 = new JLabel("");
		cicle2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\circle1.png"));
		cicle2.setBounds(24, 70, 128, 128);
		BorderPanel.add(cicle2);
		
		JLabel ChatText = new JLabel("채팅");
		ChatText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		ChatText.setBounds(72, 195, 39, 25);
		BorderPanel.add(ChatText);
		
		JLabel InoutButton = new JLabel("New label");
		InoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				InOutUi inoutui = new InOutUi();
			}
		});
		
		InoutButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\exit-door2.png"));
		InoutButton.setBounds(225, 100, 64, 64);
		BorderPanel.add(InoutButton);
		
		JLabel cicle3 = new JLabel("");
		cicle3.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\circle1.png"));
		cicle3.setBounds(194, 70, 128, 128);
		BorderPanel.add(cicle3);
		
		JLabel InoutText = new JLabel("입실/퇴실 조회");
		InoutText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		InoutText.setBounds(211, 195, 101, 25);
		BorderPanel.add(InoutText);
		
		JLabel cicle4 = new JLabel("");
		cicle4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				InquiryFoodUi inquiry = new InquiryFoodUi();
			}
		});
		
		cicle4.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\circle1.png"));
		cicle4.setBounds(364, 70, 128, 128);
		BorderPanel.add(cicle4);
		
		JLabel ForkButton = new JLabel("New label");
		ForkButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\fork-and-knife.png"));
		ForkButton.setBounds(399, 105, 64, 64);
		BorderPanel.add(ForkButton);
		
		JLabel ForkText = new JLabel("식사 요청 관리");
		ForkText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		ForkText.setBounds(380, 195, 101, 25);
		BorderPanel.add(ForkText);
	}
	public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               MainUi window = new MainUi();
	               window.frame.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }
}
