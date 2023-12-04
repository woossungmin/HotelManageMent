package View.Member;

import javax.swing.*;
import javax.swing.border.Border;

import Custom.Message;
import Custom.RoomButton;
import DTO.CheckInDto;
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

public class CheckInUi {

    private JFrame frame;
    private RoomButton[] roomButton = new RoomButton[10];
    private JComboBox<String> InBox, OutBox;

    public CheckInUi(String em) {
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

        InBox = new JComboBox<>();
        InBox.setBounds(12, 10, 225, 31);
        InBox.setBackground(new Color(255, 255, 255));
        BorderPanel4.add(InBox);
        InBox.setBorder(border);
        
        OutBox = new JComboBox<>();
        OutBox.setBounds(290, 10, 225, 31);
        OutBox.setBackground(new Color(255, 255, 255));
        BorderPanel4.add(OutBox);
        OutBox.setBorder(border);

        JLabel lblNewLabel = new JLabel("~");
        lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
        lblNewLabel.setBounds(253, 18, 30, 15);
        BorderPanel4.add(lblNewLabel);

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
            
            roomButton[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	CheckInDto checkindto = new CheckInDto();
                	
                	String InTime = InBox.getSelectedItem().toString() + " 15시";
                	String OutTime = OutBox.getSelectedItem().toString() + " 11시";
                	String RoomNum = String.valueOf(room);
                	
                	checkindto.setEmail(em);
                	checkindto.setIntime(InTime);
                	checkindto.setOutTime(OutTime);
                	checkindto.setRoomNum(RoomNum);
                	
                    CheckInStateUi checkui = new CheckInStateUi(checkindto,frame);
                }
            });
        }
        
        try {
            List<String> list = new Controller("localhost", 12347).SearchState();

            if (list != null && !list.isEmpty()) {
                for (String roomNumber : list) {
                    int roomIndex = Integer.parseInt(roomNumber) - 101;
                    
                    if (roomIndex >= 0 && roomIndex < 10) {
                        // roomIndex가 유효한 범위 내에 있다면 배경색 변경
                        roomButton[roomIndex].setBackgroundColor(new Color(84, 84, 84));
                        roomButton[roomIndex].setEnabled(false);
                    }
                }
            }
        } 
        catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
  
        
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // InBox에 날짜 추가
        for (int i = 0; i <= currentDate.lengthOfMonth(); i++) {
            LocalDate date = currentDate.plusDays(i);
            InBox.addItem(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        // OutBox에 날짜 추가 (다음 날부터 마지막 날까지)
        for (int i = 1; i <= currentDate.lengthOfMonth(); i++) {
            LocalDate date = currentDate.plusDays(i);
            OutBox.addItem(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        
        // 가운데 정렬을 위한 렌더러 설정
        DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // InBox의 렌더러 설정
        InBox.setRenderer(centerRenderer);

        // OutBox의 렌더러 설정
        OutBox.setRenderer(centerRenderer);
        
        //입실 날짜를 고르면 퇴실 날짜 자동 설정
        InBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // InBox에서 선택된 날짜 가져오기
                String selectedDate = InBox.getSelectedItem().toString();

                // 선택된 날짜의 다음 날을 계산하여 OutBox에 설정
                LocalDate nextDate = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .plusDays(1);
                OutBox.setSelectedItem(nextDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
        
    }
}
