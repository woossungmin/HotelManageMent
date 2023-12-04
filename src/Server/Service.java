package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.protocol.Resultset;

import DB.DB;
import DTO.CheckInDto;
import DTO.MemberDto;

public class Service {
    private DB db;
    private List<ClientHandler> clientHandlers; // 클라이언트 핸들러들을 저장하는 리스트
    private static int port = 12347;
    CheckInDto checkInDto;
    MemberDto memberDto;
    String useremail;
    String userName;
    String phone;
    String pw;
    String RoomNum;
    String InTime;
    String OutTime;
    

    public Service() throws ClassNotFoundException, SQLException {
        db = new DB();
        clientHandlers = new ArrayList<>();
    }

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버 시작. 포트 " + port + "에서 대기 중");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트 연결됨: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler); // 클라이언트 핸들러 리스트에 추가
                clientHandler.start();
            }
        } catch (IOException e) {
         //   e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;


        public ClientHandler(Socket socket) throws IOException {
            clientSocket = socket;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        }

        @Override
        public void run() {
            try {
                while (true) {
                	String request = null;
                    Object requestObject = in.readObject(); //***********
                    HashMap<String, Object> requestDataMap = new HashMap<>();
                    if (requestObject instanceof String){
                        request = (String) requestObject;
                    }
                    String operation;
                    String[] requestData = new String[]{};
                    if (requestObject instanceof HashMap) {
                        requestDataMap = (HashMap<String, Object>) requestObject;//***********
                        operation = (String) requestDataMap.get("operation");
                    }
                    else {
                        requestData = request.split(":");
                        operation = requestData[0];
                    }
                    System.out.println(operation);
                    switch (operation) {
                        case "CHAT":
                            String message = requestData[1];
                            System.out.println("메시지 수신: " + message);
                            // 채팅 메시지를 다른 클라이언트로 전송하는 로직을 추가합니다.
                            // 여기서는 수신한 메시지를 해당 클라이언트에 연결된 다른 클라이언트에게 보내는 방식으로 구현합니다.
                            sendChatMessageToOtherClients(message);
                            break;
                        case "SIGN_UP":
                            useremail = requestData[1];
                            userName = requestData[2];
                            phone = requestData[3];
                            pw = requestData[4];

                            memberDto = new MemberDto();

                            memberDto.setEmail(useremail);     
                            memberDto.setUserName(userName);
                            memberDto.setPhone(phone);
                            memberDto.setPw(pw);

                            boolean signupResult = db.Signup(memberDto);
                            System.out.println(signupResult);
                            out.writeObject(signupResult ? "SIGNUP_SUCCESS" : "SIGNUP_FAILURE");
                            break;
                        case "LOGIN":
                            useremail = requestData[1];
                            pw = requestData[2];
                            userName = requestData[3];

                            memberDto = new MemberDto();

                            memberDto.setEmail(useremail);
                            memberDto.setPw(pw);
                            memberDto.setUserName(userName);

                            boolean loginResult = db.Login(memberDto);
                            System.out.println(loginResult);
                            out.writeObject(loginResult ? "LOGIN_SUCCESS" : "LOGIN_FAILURE");
                            break;
                        case "SEARCH_PW":
                            useremail = requestData[1];
                            phone = requestData[2];
                            userName = requestData[3];
                            
                            memberDto = new MemberDto();

                            memberDto.setEmail(useremail);
                            memberDto.setPhone(phone);
                            memberDto.setUserName(userName);

                            String pwResult = db.SearchPw(memberDto);
                            System.out.println(pwResult+"***************");
                            System.out.println(memberDto.getEmail());
                            System.out.println(memberDto.getPhone());
                            System.out.println(memberDto.getUserName());
                            out.writeObject(!pwResult.equals("false") ? pwResult : "false");
                            break;
                        case "SEARCH_INFO":
                            useremail = requestData[1];
                            memberDto = new MemberDto();
                            memberDto.setEmail(useremail);

                            ResultSet result = db.SearchInfo(memberDto);
                            
                            if (result.next()) {

                                memberDto.setEmail(result.getString(1));
                                memberDto.setPw(result.getString(2));
                                memberDto.setPhone(result.getString(3));
                                memberDto.setUserName(result.getString(4));

                                out.writeObject(memberDto);
                            } else {
                                out.writeObject(null); // or any other indicator for no data found
                            }
                            break;
                        case "SEARCH_PRICE":
                        		RoomNum = requestData[1];

                            String price = db.SearchPrice(RoomNum);
                            
                            if (!price.equals(null)) {

                                out.writeObject(price);
                            } else {
                                out.writeObject(null); // or any other indicator for no data found
                            }
                            break;
                        case "CHECK_IN":
                            checkInDto = new CheckInDto();
                            useremail = requestData[1];
                            InTime = requestData[2];
                            OutTime= requestData[3];
                            RoomNum = requestData[4];
                            
                            
                            checkInDto.setEmail(useremail);     
                            checkInDto.setIntime(InTime);
                            checkInDto.setOutTime(OutTime);
                            checkInDto.setRoomNum(RoomNum);

                            boolean checkinResult = db.CheckIn(checkInDto);
                            System.out.println(checkinResult);
                            out.writeObject(checkinResult ? "CHECKIN_SUCCESS" : "CHECKIN_FAILURE");
                            break;
                        case "CHECK_OUT":
                        	String RoomNum = requestData[1];

                            boolean checkOutResult = db.CheckOut(RoomNum);
                            out.writeObject(checkOutResult ? "CHECKOUT_SUCCESS" : "CHECKOUT_FAILURE");
                            break;
                        case "SEARCH_STATE":
                            ResultSet stateresult = db.SearchState();
                            List<String> list = new ArrayList<>();

                            while(stateresult.next()) {
                                list.add(stateresult.getString(1));
                            }

                            out.writeObject(list);
                            break;
                        case "SEARCH_ROOMNUMBER":
                            memberDto = new MemberDto();
                            useremail = requestData[1];
                            memberDto.setEmail(useremail); 
                            ResultSet roomresult = db.SearchRoomNumber(memberDto);
                            
                            List<String> roomlist = new ArrayList<>();

                            while(roomresult.next()) {
                                roomlist.add(roomresult.getString(1));
                            }

                            out.writeObject(roomlist);
                            break;
                        case "SEARCH_MENU":
                            ResultSet menuresult = db.MenuResult();
                            List<String> menulist = new ArrayList<>();

                            while (menuresult.next()) {
                                menulist.add(menuresult.getString(1));
                                menulist.add(menuresult.getString(2));
                                menulist.add(menuresult.getString(3));

                            }

                            out.writeObject(menulist);
                            break;
                        case "INSERT_FOOD":
                            try {

                                System.out.println(requestDataMap);

                                // 여기서 DB에 데이터를 삽입하는 로직을 구현
                               boolean insertFoodResult = db.InsertFood(requestDataMap);

                                // 응답 전송
                                out.writeObject(insertFoodResult ? "INSERT_FOOD_SUCCESS" : "INSERT_FOOD_FAILURE");
                                out.flush();
                                out.close(); // 추가: 스트림 닫기
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "SEARCH_FOOD":
                            ResultSet foodresult = db.FoodResult();
                            List<String> foodlist = new ArrayList<>();

                            while (foodresult.next()) {
                            	foodlist.add(foodresult.getString(1));
                            	foodlist.add(foodresult.getString(2));
                            	foodlist.add(foodresult.getString(3));
                            	foodlist.add(foodresult.getString(4));
                            	foodlist.add(foodresult.getString(5));
                            	foodlist.add(foodresult.getString(6));
                            }

                            out.writeObject(foodlist);
                            break;
                        case "OK_FOOD":
                        	String OrderNumOk = requestData[1];
                        	String RoomNumOk = requestData[2];
                        	String FoodNameOk = requestData[3];

                            boolean OkResult = db.OkFood(OrderNumOk,RoomNumOk,FoodNameOk);
                            out.writeObject(OkResult ? "OK_FOOD_SUCCESS" : "OK_FOOD_FAILURE");
                            break;
                        case "CANCLE_FOOD":
                        	String OrderNumCancle = requestData[1];
                        	String RoomNumCancle = requestData[2];
                        	String FoodNameCancle = requestData[3];

                            boolean CancleResult = db.CancleFood(OrderNumCancle,RoomNumCancle,FoodNameCancle);
                            out.writeObject(CancleResult ? "CANCLE_FOOD_SUCCESS" : "CANCLE_FOOD_FAILURE");
                            break;
                        case "SEARCH_ROOM":
                            ResultSet Searchroomresult = db.RoomResult();
                            List<String> Searchroomlist = new ArrayList<>();

                            while (Searchroomresult.next()) {
                            	Searchroomlist.add(Searchroomresult.getString(1));
                            	Searchroomlist.add(Searchroomresult.getString(2));
                            	Searchroomlist.add(Searchroomresult.getString(3));
                            	Searchroomlist.add(Searchroomresult.getString(4));
                            }

                            out.writeObject(Searchroomlist);
                            break;
                        case "MEMBER_UPDATE":
                        	 useremail = requestData[1];
                             userName = requestData[2];
                             phone = requestData[3];
                             pw = requestData[4];
                             String em = requestData[5];

                             memberDto = new MemberDto();

                             memberDto.setEmail(useremail);     
                             memberDto.setUserName(userName);
                             memberDto.setPhone(phone);
                             memberDto.setPw(pw);

                             boolean memberResult = db.MemberUpdate(memberDto,em);

                            out.writeObject(memberResult ? "UPDATE_SUCCESS" : "UPDATE_FAILURE");
                            break;
                            
                    }
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
               // e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                   // e.printStackTrace();
                }
            }
        }
        private void sendChatMessageToOtherClients(String message) throws IOException {
            // 모든 클라이언트 핸들러를 순회하며 수신한 메시지를 전달합니다.
            for (ClientHandler clientHandler : clientHandlers) {
                if (clientHandler != this) {
                    clientHandler.out.writeObject("CHAT:" + message);
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Service server = new Service();
        server.startServer(port);
    }
}