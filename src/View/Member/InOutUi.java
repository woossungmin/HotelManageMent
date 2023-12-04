package View.Member;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InOutUi {

	private JFrame frame;

	public InOutUi(String em) {
		initialize(em);
	}

	private void initialize(String em) {
		frame = new JFrame();
		frame.setBounds(380, 120, 565, 440);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("입실/퇴실");
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
		
		JLabel OutImage = new JLabel("New label");
		OutImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				CheckOutUi checkoutui = new CheckOutUi(em);
			}
		});
		
		OutImage.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\exit-door.png"));
		OutImage.setBounds(339, 125, 64, 64);
		BorderPanel.add(OutImage);
		
		JLabel InImage = new JLabel("New label");
		InImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				CheckInUi checkinui = new CheckInUi(em);
			}
		});
		
		InImage.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\exit-door1.png"));
		InImage.setBounds(117, 125, 64, 64);
		BorderPanel.add(InImage);
		
		JLabel CicleImage1 = new JLabel("New label");
		CicleImage1.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\circle.png"));
		CicleImage1.setBounds(310, 93, 128, 128);
		BorderPanel.add(CicleImage1);
		
		JLabel CicleImage2 = new JLabel("New label");
		CicleImage2.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\circle.png"));
		CicleImage2.setBounds(88, 93, 128, 128);
		BorderPanel.add(CicleImage2);
		
		JLabel InText = new JLabel("입실하기");
		InText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		InText.setBounds(114, 220, 102, 35);
		BorderPanel.add(InText);
		
		JLabel OutText = new JLabel("퇴실하기");
		OutText.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		OutText.setBounds(339, 220, 102, 35);
		BorderPanel.add(OutText);
		
		JPanel timepanel = new JPanel();
		timepanel.setBounds(0, 0, 527, 35);
		BorderPanel.add(timepanel);
		timepanel.setBorder(border);
		timepanel.setBackground(new Color(255,255,255));
		timepanel.setLayout(null);
		
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
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\delete-button.png"));
		BackButton.setBounds(495, 5, 24, 24);
		timepanel.add(BackButton);
		
		JLabel lblNewLabel = new JLabel("환영합니다. 베니키아입니다!");
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		lblNewLabel.setBounds(170, 10, 197, 15);
		timepanel.add(lblNewLabel);
		
	}
}
