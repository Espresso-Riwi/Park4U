package App.src;    

import javax.swing.*;
import java.util.ArrayList;

public class Menu {
    static String level = "";
    public void menu(){
        Parking p1 = new Parking("Level 1", 30);
        Parking p2 = new Parking("Level 2", 30);
        Parking p3 = new Parking("Level 3", 30);
        Parking p4 = new Parking("Level 4", 30);

        ArrayList<Vehicle> todayVehiclesList = new ArrayList<>();

        Entrance e = new Entrance();
        Exit ex = new Exit();

        String[] options = {"Entrance", "Membership", "Agreement", "Out", "Reports","Exit"};
        boolean flag = true;

        while(flag){
            String option = JOptionPane.showInputDialog(null, "Welcome to Park4U\nChoose the option that you want: ", "Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
            switch (option){
                case "Entrance":
                    level = e.chooseParking(p1, p2, p3, p4);
                    e.entrance(level, knowLevel(level, p1, p2, p3, p4), todayVehiclesList);
                    break;
                case "Membership":
                    Membership.membershipsMenu(todayVehiclesList);
                    break;
                case "Agreement":
                    break;
                case "Out":
                    level = e.chooseParking(p1, p2, p3, p4);
                    ex.exit(knowLevel(level, p1, p2, p3, p4));
                    break;
                case "Reports":
                    Reports.reportsMenu(p1, p2, p3, p4, todayVehiclesList);
                    break;
                case "Exit":
                    flag = false;
                    break;
            }
        }
    }

    public Parking knowLevel(String level, Parking p1, Parking p2, Parking p3, Parking p4){
        return switch (level) {
            case "Level 2" -> p2;
            case "Level 3" -> p3;
            case "Level 4" -> p4;
            default -> p1;
        };
    }
}
