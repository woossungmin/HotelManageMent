package View.Manager;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class ManagerChatUi extends JFrame {
	
    private JTextField textField;
    JTextArea textArea;
    JLabel lblStatus;
    
    private ServerSocket serverSocket;
    private Socket socket;

    private DataInputStream REQ_CHAT;
    private DataOutputStream ACK_CHAT;

    private boolean connectStatus;// 클라이언트 접속 여부 저장
    private boolean stopSignal;// 쓰레드 종료 신호 저장

    public ManagerChatUi() {
        showFrame();
        startService();// 채팅 서버 시작
    }

    public void showFrame() {
        setTitle("1:1 채팅 [프론트]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 400, 500, 300);
        getContentPane().setLayout(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 23, 484, 208);
        getContentPane().add(scrollPane);

        textField = new JTextField();
        textField.setBounds(0, 230, 390, 31);
        getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnInput = new JButton("입력");
        btnInput.setBackground(Color.YELLOW);
        btnInput.setForeground(Color.BLACK);
        btnInput.setBounds(387, 230, 97, 31);
        getContentPane().add(btnInput);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 484, 22);
        getContentPane().add(panel);

        lblStatus = new JLabel("클라이언트 연결 상태 : 연결없음");
        lblStatus.setForeground(Color.RED);
        panel.add(lblStatus);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        setVisible(true);

        textField.requestFocus();
    }


    public void startService() {
        try {
            // ServerSocket 객체를 생성하여 지정된 포트(59876)를 개방
            serverSocket = new ServerSocket(59876);

            // 클라이언트로부터 접속이 성공할 때까지 접속 무한 대기
            connectStatus = false;
            
            while (!connectStatus) {
                textArea.append("클라이언트 접속 대기중...\n");
                // ServerSocket 객체의 accept()메서드를 호출하여 연결대기
                // 연결 완료 시 Socket 객체 리턴됨
                socket = serverSocket.accept();
                // 접속된 클라이언트에 대한 IP 주소 정보 출력
                textArea.append("클라이언트가 접속 하였습니다. (" + socket.getInetAddress() + ")\n");

                // DataInputStream 객체 생성 후 입력되는 패스워드 가져와서 출력
                REQ_CHAT = new DataInputStream(socket.getInputStream());

                // DataOutStream 객체 생성
                ACK_CHAT = new DataOutputStream(socket.getOutputStream());

                connectStatus=true;
                
                textArea.append("================================채팅================================\n");                
                lblStatus.setText("클라이언트 연결 상태 : 연결됨");  
                
                ACK_CHAT.writeBoolean(true);
            }

            //Runnable 인터페이스를 구현하여 Thread 객체에 전달 후 start() 메서드 호출
            //바로 쓰레드 실행까지 처리됨
            new Thread(new Runnable() {

                @Override
                public void run() {
                    //클라이언트로부터 전송되는 메세지를 처리할 receiveMessage() 메서드 호출
                    receiveMessage();
                }
            }).start();

        } 
        catch (IOException e) {
           // e.printStackTrace();
        }

    }
    public void receiveMessage() {
        //멀티 쓰레딩으로 메세지 수신 처리 작업 수행
        //boolean 타입 멤버변수 stopSignal 이 false 일 동안 반복해서 메세지 수신
        try {  	
            while(!stopSignal) {
                //클라이언트가 writeUTF() 메서드로 전송한 메세지를 입력받기
                textArea.append("고객 : "+REQ_CHAT.readUTF()+"\n");
            }
            //stopSignal 이 true 가 되면 메세지 수신 종료되므로 REQ_CHAT와 socket 반환
            REQ_CHAT.close();
            socket.close();   
        }
        catch(EOFException e){
            //상대방이 접속 해제할 경우 소켓이 제거되면서 호출되는 예외
            textArea.append("클라이언트 접속이 해제되었습니다.\n");
            lblStatus.setText("클라이언트 연결 상태 : 미연결");
            connectStatus=false;
        }
        catch(SocketException e) {
            textArea.append("서버 접속이 해제되었습니다.\n");
        }
        catch (IOException e) {
           // e.printStackTrace();
        }
    }
    public void sendMessage() {
        try {
            String text = textField.getText();
            textArea.append(">> " + text + "\n");

            //입력된 메세지가 "/exit" 일 경우
            if(text.equals("/exit")) {
                //textArea 에 "bye" 출력 후
                //stopSignal을 true로 설정 , 스트림 반환, 소켓 반환
                stopSignal=true;
                ACK_CHAT.close();
                socket.close();

                //프로그램 종료
                System.exit(0);
            }else {
                //입력된 메세지가 "/exit"가 아닐 경우( 전송할 메세지인 경우)
                //클라이언트에게 메세지 전송
                ACK_CHAT.writeUTF(text);

                //초기화 및 커서요청
                textField.setText("");
                textField.requestFocus();

            }

        } catch (IOException e) {
           // e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new ManagerChatUi();
    }
}