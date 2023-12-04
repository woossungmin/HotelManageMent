package View.Member;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUi {

	private JFrame frame;

	public MainUi(String em) {
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
	      
	    Border border =  BorderFactory.createLineBorder(new Color(0,0,0),3);
		
		JPanel BorderPanel = new JPanel();
		BorderPanel.setBounds(0, 0, 551, 403);
		frame.getContentPane().add(BorderPanel);
		BorderPanel.setBackground(new Color(255,255,255));
		BorderPanel.setLayout(null);
		
		JPanel BorderPanel1 = new JPanel();
		BorderPanel1.setBounds(12, 10, 65, 65);
		BorderPanel.add(BorderPanel1);
		BorderPanel1.setBorder(border);
		BorderPanel1.setBackground(new Color(255,255,255));
		BorderPanel1.setLayout(null);
		
		JPanel BorderPanel2 = new JPanel();
		BorderPanel2.setBounds(74, 10, 405, 65);
		BorderPanel.add(BorderPanel2);
		BorderPanel2.setBorder(border);
		BorderPanel2.setBackground(new Color(255,255,255));
		BorderPanel2.setLayout(null);
		
		JPanel BorderPanel3 = new JPanel();
		BorderPanel3.setBounds(474, 10, 65, 65);
		BorderPanel.add(BorderPanel3);
		BorderPanel3.setBorder(border);
		BorderPanel3.setBackground(new Color(255,255,255));
		BorderPanel3.setLayout(null);
		
		JPanel BorderPanel4 = new JPanel();
		BorderPanel4.setBounds(12, 73, 527, 322);
		BorderPanel.add(BorderPanel4);
		BorderPanel4.setBorder(border);
		BorderPanel4.setBackground(new Color(255,255,255));
		BorderPanel4.setLayout(null);
		
		JLabel title = new JLabel("B");
		title.setForeground(new Color(64, 64, 64));
		title.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		title.setBounds(137, 15, 40, 37);
		BorderPanel2.add(title);
		
		JLabel title1 = new JLabel("A");
		title1.setForeground(new Color(64, 64, 64));
		title1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 39));
		title1.setBounds(255, 16, 40, 37);
		BorderPanel2.add(title1);
		
		JLabel title2 = new JLabel("ENIKE");
		title2.setForeground(new Color(64, 64, 64));
		title2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 33));
		title2.setBounds(162, 17, 99, 37);
		BorderPanel2.add(title2);		
		
		JLabel InfoButton = new JLabel("");
		InfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				InfoUi infoui = new InfoUi(em);
			}
		});
		
		InfoButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\user.png"));
		InfoButton.setBounds(16, 17, 32, 32);
		BorderPanel1.add(InfoButton);
		
		JLabel BackButton = new JLabel("");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				LoginUi loginui = new LoginUi();
			}
		});
		
		BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\log-in.png"));
		BackButton.setBounds(0, 0, 64, 64);
		BorderPanel3.add(BackButton);
		
		JLabel InoutButton = new JLabel("");
		InoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				InOutUi inoutbutton = new InOutUi(em);
			}
		});
		
		InoutButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\check-box.png"));
		InoutButton.setBounds(30, 84, 128, 128);
		BorderPanel4.add(InoutButton);
		
		JLabel MenuButton = new JLabel("New label");
		MenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MenuUi menuui = new MenuUi(em);
			}
		});
		
		MenuButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\restaurant-interface-symbol-of-fork-and-knife-couple.png"));
		MenuButton.setBounds(200, 84, 128, 128);
		BorderPanel4.add(MenuButton);
		
		JLabel ChatButton = new JLabel("New label");
		ChatButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				MemberChatUi memberchatui = new MemberChatUi();
			}
		});
		
		ChatButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\chat.png"));
		ChatButton.setBounds(370, 84, 128, 128);
		BorderPanel4.add(ChatButton);
		
		JLabel InOutTitle = new JLabel("입실 / 퇴실");
		InOutTitle.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		InOutTitle.setBounds(35, 230, 128, 40);
		BorderPanel4.add(InOutTitle);
		
		JLabel MenuTitle = new JLabel("식사 요청");
		MenuTitle.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		MenuTitle.setBounds(225, 230, 109, 40);
		BorderPanel4.add(MenuTitle);
		
		JLabel ChatTitle = new JLabel("채팅");
		ChatTitle.setFont(new Font("한컴산뜻돋움", Font.BOLD, 25));
		ChatTitle.setBounds(410, 230, 66, 40);
		BorderPanel4.add(ChatTitle);		
		
	}
    public JFrame getFrame() {
        return frame;
    }
}
