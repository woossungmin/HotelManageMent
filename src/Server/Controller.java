package Server;

import DTO.CheckInDto;
import DTO.FoodRequestDto;
import DTO.MemberDto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Member;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;

public class Controller {
	
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Controller(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    
    public void sendChatMessage(String message) throws IOException, ClassNotFoundException {
        String request = "CHAT:" + message;
        out.writeObject(request);
    }
    
    public void sendConsultMessage(String message) throws IOException, ClassNotFoundException {
        String request = "Consult:" + message;
        out.writeObject(request);
    }

    public boolean checkOverlap(String ID) throws IOException, ClassNotFoundException {
        String request = "CHECK_OVERLAP:" + ID;
        
        out.writeObject(request);

        String response = (String) in.readObject();

        return response.equals("ID_NOT_EXISTS");
    }
    
    public boolean Signup(MemberDto memberDto) throws IOException, ClassNotFoundException {
        String request = "SIGN_UP:" + memberDto.getEmail() +":" + memberDto.getUserName() +":"+ memberDto.getPhone() +":" + memberDto.getPw();
       
        out.writeObject(request);

        String response = (String) in.readObject();

        return response.equals("SIGNUP_SUCCESS");
    }
    
    public boolean LogIn(MemberDto memberDto) throws IOException, ClassNotFoundException {
        String request = "LOGIN:" + memberDto.getEmail() + ":" + memberDto.getPw() + ":" + memberDto.getUserName();
        
        out.writeObject(request);

        String response = (String) in.readObject();

        return response.equals("LOGIN_SUCCESS");

    }
    
    public String SearchPw(MemberDto memberDto) throws IOException, ClassNotFoundException {
        String request = "SEARCH_PW:" + memberDto.getEmail() + ":" + memberDto.getPhone() + ":" + memberDto.getUserName();
        
        out.writeObject(request);

        String response = (String) in.readObject();

        return response;

    }
    
    public MemberDto SearchInfo(MemberDto memberDto) throws IOException, ClassNotFoundException {
        String request = "SEARCH_INFO:" + memberDto.getEmail();
        
        try {
            out.writeObject(request);
            
            Object response = in.readObject();

            if (response instanceof MemberDto) {
                MemberDto receivedMember = (MemberDto) response;
                
                System.out.println("Member found: " + receivedMember.toString());
                
                return receivedMember;
            } 
            else {
                System.out.println("Error: Unexpected response from the server");
                System.out.println("Received response: " + response);
                return null;
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e; 
        }
    }
    
    public String SearchPrice(String RoomNum) throws IOException, ClassNotFoundException {
        String request = "SEARCH_PRICE:" + RoomNum;
        
        out.writeObject(request);

        String response = (String) in.readObject();

        return response;
    }
    
    public boolean CheckIn(CheckInDto checkinDto) throws IOException, ClassNotFoundException {
        String request = "CHECK_IN:" + checkinDto.getEmail() +":" + checkinDto.getIntime() +":"+ checkinDto.getOutTime() +":" + checkinDto.getRoomNum();
        
        out.writeObject(request);

        String response = (String) in.readObject();

        return response.equals("CHECKIN_SUCCESS");
    }
    
    public List SearchState() throws IOException, ClassNotFoundException {
        String request = "SEARCH_STATE:";
        
        try {
            out.writeObject(request);
            
            Object response = in.readObject();

            if (response instanceof List<?>) {
                List<String> list = (List<String>) response;
                
                return list;
            } 
            else {
                System.out.println("Error: Unexpected response from the server");
                System.out.println("Received response: " + response);
                
                return null;
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e; 
        }
    }
    
    public List SearchRoomNumber(MemberDto memberDto) throws IOException, ClassNotFoundException {
        String request = "SEARCH_ROOMNUMBER:" + memberDto.getEmail();
        
        try {
            out.writeObject(request);
            
            Object response = in.readObject();

            if (response instanceof List<?>) {
                List<String> list = (List<String>) response;
                
                return list;
            } 
            else {
                System.out.println("Error: Unexpected response from the server");
                System.out.println("Received response: " + response);
                
                return null;
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e; 
        }
    }
    
    public Boolean CheckOut(String RoomNumber) throws IOException, ClassNotFoundException {
        String request = "CHECK_OUT:" + RoomNumber;
        
        out.writeObject(request);
        
        String response = (String) in.readObject();

        return response.equals("CHECKOUT_SUCCESS");
    }
    public List<String> SearchMenu() throws IOException, ClassNotFoundException {
        String request = "SEARCH_MENU:";

        try {
            out.writeObject(request);

            Object response = in.readObject();

            if (response instanceof List<?>) {
                List<String> list = (List<String>) response;
                
                return list;
            } else {
                System.out.println("Error: 서버로부터 예상치 못한 응답을 받았습니다");
                System.out.println("받은 응답: " + response);
                
                return new ArrayList<>(); 
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e;
        }
    }
    
    public boolean InsertFood(String em, String roomNum, List<Map<String, String>> totalFoodList) throws IOException, ClassNotFoundException {
        try {
            // 서버로 전송할 request 객체 생성
            Map<String, Object> requestObject = new HashMap<>();
            
            requestObject.put("operation", "INSERT_FOOD");
            requestObject.put("email", em);
            requestObject.put("roomNum", roomNum);
            requestObject.put("foodList", totalFoodList);

            // 서버로 전송
            out.writeObject(requestObject);
            out.flush(); // 버퍼 비우기

            // 서버로부터 응답 받기
            String response = (String) in.readObject();

            return response.equals("INSERT_FOOD_SUCCESS");
        } 
        catch (IOException e) {
            e.printStackTrace();
            
            throw e;
        }
    }
    public List<String> SearchFood() throws IOException, ClassNotFoundException {
        String request = "SEARCH_FOOD:";

        try {
            out.writeObject(request);

            Object response = in.readObject();

            if (response instanceof List<?>) {
                List<String> list = (List<String>) response;
                
                return list;
            } else {
                System.out.println("Error: 서버로부터 예상치 못한 응답을 받았습니다");
                System.out.println("받은 응답: " + response);
                
                return new ArrayList<>(); // 빈 리스트 반환 또는 다른 처리 방법 선택
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e;
        }
    }
	public boolean Okfood(FoodRequestDto foodRequestDto) throws IOException, ClassNotFoundException {
        String request = "OK_FOOD:" + foodRequestDto.getOrderNum() + ":" + foodRequestDto.getRoomNum() + ":" + foodRequestDto.getFoodName();
        
        out.writeObject(request);
        
        String response = (String) in.readObject();

        return response.equals("OK_FOOD_SUCCESS");
	}
	public boolean Canclefood(FoodRequestDto foodRequestDto) throws IOException, ClassNotFoundException {
        String request = "CANCLE_FOOD:" + foodRequestDto.getOrderNum() + ":" + foodRequestDto.getRoomNum() + ":" + foodRequestDto.getFoodName();
        
        out.writeObject(request);
        
        String response = (String) in.readObject();

        return response.equals("CANCLE_FOOD_SUCCESS");
	}
    public List<String> SearchRoom() throws IOException, ClassNotFoundException {
        String request = "SEARCH_ROOM:";

        try {
            out.writeObject(request);

            Object response = in.readObject();

            if (response instanceof List<?>) {
                List<String> list = (List<String>) response;
                
                return list;
            } 
            else {
                System.out.println("Error: 서버로부터 예상치 못한 응답을 받았습니다");
                System.out.println("받은 응답: " + response);
                
                return new ArrayList<>(); // 빈 리스트 반환 또는 다른 처리 방법 선택
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            
            throw e;
        }
    }
    public boolean MemberUpdate(MemberDto memberDto, String em) throws IOException, ClassNotFoundException {
        String request = "MEMBER_UPDATE:" + memberDto.getEmail() +":" + memberDto.getUserName() +":"+ memberDto.getPhone() +":" + memberDto.getPw() +":" + em;
       
        out.writeObject(request);

        String response = (String) in.readObject();

        return response.equals("UPDATE_SUCCESS");
    }
}