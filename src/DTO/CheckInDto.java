package DTO;

import java.io.Serializable;

public class CheckInDto implements Serializable {

    private String email;
    private String Intime;
    private String OutTime;
    private String RoomNum;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntime() {
        return Intime;
    }

    public void setIntime(String Intime) {
        this.Intime = Intime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String OutTime) {
        this.OutTime = OutTime;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(String RoomNum) {
        this.RoomNum = RoomNum;
    }
}
