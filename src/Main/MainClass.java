package Main;

import java.awt.EventQueue;

import View.Member.LoginUi;

public class MainClass {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
        	
            LoginUi login = new LoginUi();
        });
    }
}
