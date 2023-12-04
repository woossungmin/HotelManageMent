package View.Member;

import javax.swing.*;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoomButton;
import DTO.CheckInDto;
import DTO.MemberDto;
import Server.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CheckOutUi {

    private JFrame frame;
    private RoomButton[] roomButton = new RoomButton[10];

    public CheckOutUi(String em) {
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

        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0), 3);

        JPanel BorderPanel = new JPanel();
        BorderPanel.setBounds(0, 0, 551, 403);
        frame.getContentPane().add(BorderPanel);
        BorderPanel.setBackground(new Color(255, 255, 255));
        BorderPanel.setLayout(null);

        JPanel BorderPanel1 = new JPanel();
        BorderPanel1.setBounds(12, 10, 65, 65);
        BorderPanel.add(BorderPanel1);
        BorderPanel1.setBorder(border);
        BorderPanel1.setBackground(new Color(255, 255, 255));
        BorderPanel1.setLayout(null);

        JPanel BorderPanel2 = new JPanel();
        BorderPanel2.setBounds(74, 10, 405, 65);
        BorderPanel.add(BorderPanel2);
        BorderPanel2.setBorder(border);
        BorderPanel2.setBackground(new Color(255, 255, 255));
        BorderPanel2.setLayout(null);

        JPanel BorderPanel3 = new JPanel();
        BorderPanel3.setBounds(474, 10, 65, 65);
        BorderPanel.add(BorderPanel3);
        BorderPanel3.setBorder(border);
        BorderPanel3.setBackground(new Color(255, 255, 255));
        BorderPanel3.setLayout(null);

        JPanel BorderPanel4 = new JPanel();
        BorderPanel4.setBounds(12, 73, 527, 322);
        BorderPanel.add(BorderPanel4);
        BorderPanel4.setBorder(border);
        BorderPanel4.setBackground(new Color(255, 255, 255));
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

        JLabel BackButton = new JLabel("");
        BackButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                InOutUi inoutUi = new InOutUi(em);
            }
        });
        
        BackButton.setIcon(new ImageIcon("C:\\Users\\user\\Desktop\\호텔관리프로그램 이미지\\log-in.png"));
        BackButton.setBounds(0, 0, 64, 64);
        BorderPanel3.add(BackButton);

        int[] xroomButton = {42, 42, 136, 136, 230, 230, 324, 324, 418, 418};
        int[] yroomButton = {84, 213, 84, 213, 84, 213, 84, 213, 84, 213};

        for (int i = 0; i < 10; i++) {
            roomButton[i] = new RoomButton();
            roomButton[i].setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
            roomButton[i].setBounds(xroomButton[i], yroomButton[i], 65, 62);
            BorderPanel4.add(roomButton[i]);
            
            final int room = i + 101;
            
            roomButton[i].setText(String.valueOf(room + "호"));
            roomButton[i].setEnabled(false);
            
            roomButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	CheckOutStateUi checkoutstateui = new CheckOutStateUi(String.valueOf(room),frame);
                }
            });
        }
        try {
            MemberDto memberDto = new MemberDto();
            memberDto.setEmail(em);
            
            //현재 입실 중인 룸 정보와 입실 기록 정보를 비교하여 현재 입실중인 사람을 확인
            List<String> list = new Controller("localhost", 12347).SearchState();
            List<String> roomlist = new Controller("localhost", 12347).SearchRoomNumber(memberDto);

            if (list != null && !list.isEmpty() && roomlist != null && !roomlist.isEmpty()) {
                for (String roomNumber : list) {
                    if (roomlist.contains(roomNumber)) {
                    	
                        int roomIndex = Integer.parseInt(roomNumber) - 101;
                        
                        if (roomIndex >= 0 && roomIndex < 10) {
                            // roomIndex가 유효한 범위 내에 있다면 배경색 변경
                            roomButton[roomIndex].setBackgroundColor(new Color(84, 84, 84));
                            roomButton[roomIndex].setEnabled(true);
                        }
                    }
                }
            }
        } 
        catch (IOException | ClassNotFoundException e1) {
            //e1.printStackTrace();
        }        
    }
}
