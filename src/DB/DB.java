package DB;

import DTO.CheckInDto;
import DTO.MemberDto;

import javax.swing.*;

import java.security.SecureRandom;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    PreparedStatement pstmt;
    Connection conn = null;

    public DB() throws ClassNotFoundException, SQLException {
        String dbUrl = "jdbc:mysql://localhost:3316/hotel";
        String dbID = "root";
        String dbPassword = "0000";
        
        conn = DriverManager.getConnection(dbUrl, dbID, dbPassword);
    }

    public boolean Login(MemberDto memberDto) throws SQLException {
        String sql = "select * from user_info where email = ? and pw = ?";
        
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, memberDto.getEmail());
        pstmt.setString(2, memberDto.getPw());
        
        ResultSet rs = pstmt.executeQuery();
       
        if (rs.next()) {
            // Success
            return true;
        } else {
            // Failure
            return false;
        }
    }
    
    public boolean Signup(MemberDto memberDto) {
        String sql = "INSERT INTO user_info(email, pw, phone, userName) VALUES (?, ?, ?, ?)";

          try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberDto.getEmail());
            pstmt.setString(2, memberDto.getPw());
            pstmt.setString(3, memberDto.getPhone());
            pstmt.setString(4, memberDto.getUserName());

            int rs = pstmt.executeUpdate();

            return rs == 1;
        } catch (SQLException e) {
            // Handle the exception or log it for debugging
            e.printStackTrace();
            return false;
        }
    }
        
    public String SearchPw(MemberDto memberDto) throws SQLException {
        String sql = "SELECT pw FROM user_info WHERE email = ? AND phone = ? AND userName = ?";
        
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, memberDto.getEmail());
        pstmt.setString(2, memberDto.getPhone());
        pstmt.setString(3, memberDto.getUserName());

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String pw = rs.getString(1);
            return pw;
        }
        return "false";
    }
    
    public ResultSet SearchInfo(MemberDto memberDto) throws SQLException {
            String sql = "SELECT email, pw, phone, userName FROM user_info WHERE email = ?";
            
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, memberDto.getEmail());

        ResultSet rs = pstmt.executeQuery();
        
        return rs;
    }
    
    public String SearchPrice(String RoomNum) throws SQLException {
        String sql = "SELECT price FROM room WHERE roomNum = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, RoomNum);

        ResultSet rs = pstmt.executeQuery();
        
        int price = 0; 
        
        if (rs.next()) {
            price = rs.getInt(1);
        }
        
        return String.valueOf(price);
    }

    public boolean CheckIn(CheckInDto checkinDto) {
        String sql1 = "INSERT INTO check_inout(email, checkIn_time, checkOut_time, roomNum) VALUES (?, ?, ?, ?)";
        String sql2 = "UPDATE room SET state = '1' WHERE roomNum = ?";

        try (PreparedStatement pstmt1 = conn.prepareStatement(sql1);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            pstmt1.setString(1, checkinDto.getEmail());
            pstmt1.setString(2, checkinDto.getIntime());
            pstmt1.setString(3, checkinDto.getOutTime());
            pstmt1.setString(4, checkinDto.getRoomNum());
            
            int rs1 = pstmt1.executeUpdate();
            
            if (rs1 == 1) {                
                pstmt2.setString(1, checkinDto.getRoomNum());
                
                int rs2 = pstmt2.executeUpdate();
               
                if (rs2 == 1) {
                	return true;
                }
            }
            return false;
        } catch (SQLException e) {           
            e.printStackTrace();
            
            return false;
        }
        
    }
    public ResultSet SearchState() throws SQLException {
        String sql = "SELECT roomNum FROM room WHERE state = '1'";
        
        pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        
        return rs;
    }

    public boolean CheckOut(String RoomNum) {
        String sql = "UPDATE room SET state = '0' WHERE roomNum = ?";
        String sql2 = "UPDATE check_inout SET checkOut_time = '0' WHERE roomNum = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
        	 	PreparedStatement pstmt1 = conn.prepareStatement(sql2);
        	
        	pstmt.setString(1, RoomNum);
        	pstmt1.setString(1, RoomNum);
        	
            int rs = pstmt.executeUpdate();
            int rs1 = pstmt1.executeUpdate();
      
            if (rs == 1) {
            	return true;
            }
            
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
        
    }
    
    public ResultSet SearchRoomNumber(MemberDto memberDto) throws SQLException {
        String sql = "SELECT roomNum FROM check_inout WHERE email = ?";
        
        pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, memberDto.getEmail());

        ResultSet rs = pstmt.executeQuery();
        
        return rs;
    }
    
    public ResultSet MenuResult() throws SQLException {
        String sql = "SELECT * FROM food_menu";
        
        pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
         	
        return rs;
    }
    
    public boolean InsertFood(HashMap<String, Object> requestDataMap) {
        String sql = "INSERT INTO food_request(orderNum, email, foodName, counts, price,roomNum) VALUES (?, ?, ?, ?, ?,?)";

        String orderNum = null;
        int rs = 0;
        String email = null;
        String roomNum = null;
        int num = 0;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Map.Entry<String, Object> entry : requestDataMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                
                orderNum = generateRandomOrderNumber();
                
                if (value instanceof List) {
                	
                    List<Map<String, Object>> foodList = (List<Map<String, Object>>) value;
                    
                    if(num == 0) {
                    	
                    	email = String.valueOf(requestDataMap.get("email"));
                    	roomNum = String.valueOf(requestDataMap.get("roomNum"));
                    	num++;
                    }
                    for (Map<String, Object> foodItem : foodList) {
                        String foodPrice = String.valueOf(foodItem.get("foodPrice")).replaceAll(",", "").replaceAll("원", "");
                        String foodName = String.valueOf(foodItem.get("foodName"));
                        String count = String.valueOf(foodItem.get("count"));
                      

                        pstmt.setString(1, orderNum);
                        pstmt.setString(2, email);
                        pstmt.setString(3, foodName);
                        pstmt.setString(4, count);
                        pstmt.setString(5, foodPrice);
                        pstmt.setString(6, roomNum);

                        rs = pstmt.executeUpdate();
                    }
                } else {
                    System.out.println(value);
                }
            }


            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
    }
    
    public ResultSet FoodResult() throws SQLException {
        String sql = "SELECT roomNum,foodName,price,counts,complete,orderNum FROM food_request where complete = '0'";
        
        pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
         	
        return rs;
    }

    private String generateRandomOrderNumber() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[10];
        
        secureRandom.nextBytes(randomBytes);
        
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

	public boolean OkFood(String orderNumOk, String roomNumOk, String foodNameOk) {
		  String sql = "UPDATE food_request SET complete = '1' WHERE orderNum = ? and foodName = ? and roomNum = ?";

	        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        	
	        	pstmt.setString(1, orderNumOk);
	        	pstmt.setString(2, foodNameOk);
	        	pstmt.setString(3, roomNumOk);
	        	
	            int rs = pstmt.executeUpdate();	 
	            
	            if (rs == 1) {
	            	return true;
	            }
	            return false;
	        } catch (SQLException e) {	            
	            e.printStackTrace();
	            
	            return false;
	        }
	}
	public boolean CancleFood(String orderNumOk, String roomNumOk, String foodNameOk) {
		  String sql = "DELETE FROM food_request WHERE orderNum = ? and foodName = ? and roomNum = ?";

	        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
	        	
	        	pstmt.setString(1, orderNumOk);
	        	pstmt.setString(2, foodNameOk);
	        	pstmt.setString(3, roomNumOk);
	        	
	            int rs = pstmt.executeUpdate();
	            
	            if (rs == 1) {
	            	return true;
	            }
	            return false;
	        } catch (SQLException e) {	            
	            e.printStackTrace();
	            
	            return false;
	        }
	}
    public ResultSet RoomResult() throws SQLException {
        String sql = "SELECT c.email, c.roomNum, c.checkIn_time, c.checkOut_time FROM room r JOIN check_inout c ON r.roomNum = c.roomNum WHERE r.state = 1 AND c.checkOut_time != '0'";
        
        pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
                
        return rs;
    }
    
    public boolean MemberUpdate(MemberDto memberDto,String em) {
        String sql = "UPDATE user_info SET email = ?, phone = ?, pw = ?, userName = ? WHERE email = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
        	
        	pstmt.setString(1, memberDto.getEmail());
        	pstmt.setString(2, memberDto.getPhone());
        	pstmt.setString(3, memberDto.getPw());
        	pstmt.setString(4, memberDto.getUserName());
        	pstmt.setString(5, em);
        	
            int rs = pstmt.executeUpdate();

            if (rs == 1) {
            	return true;
            }
            
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            
            return false;
        }
        
    }


}