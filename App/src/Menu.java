import javax.swing.*;
import java.util.HashMap;

public class Menu {
    static String level = "";
    public void menu(){

        HashMap<String, HashMap<String, String>> parkingLevel1 = new HashMap<>();
        HashMap<String, HashMap<String, String>> parkingLevel2 = new HashMap<>();
        HashMap<String, HashMap<String, String>> parkingLevel3 = new HashMap<>();
        HashMap<String, HashMap<String, String>> parkingLevel4 = new HashMap<>();



        Entrance e = new Entrance();
        String[] options = {"Entrance", "Membership", "Agreement", "Out","Exit"};
        boolean flag = true;

        while(flag){
            String option = JOptionPane.showInputDialog(null, "Welcome to Park4U\nChoose the option that you want: ", "Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
            switch (option){
                case "Entrance":
                    e.entrance(chooseLevel(parkingLevel1, parkingLevel2, parkingLevel3, parkingLevel4), level);
                    break;
                case "Membership":
                    break;
                case "Agreement":
                    break;
                case "Out":
                    break;
                case "Exit":
                    flag = false;
                    break;
            }
        }




    }

    static public HashMap<String, HashMap<String, String>> chooseLevel(HashMap<String, HashMap<String, String>> parkingLevel1,
                                                                HashMap<String, HashMap<String, String>> parkingLevel2,
                                                                HashMap<String, HashMap<String, String>> parkingLevel3,
                                                                HashMap<String, HashMap<String, String>> parkingLevel4){
        String[] options = {"Level 1", "Level 2", "Level 3", "Level 4"};
        String option = JOptionPane.showInputDialog(null, "Choose the level that you want to park the vehicle: ", "Parking level", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option) {
            case "Level 2" -> {
                level = "B";
                return parkingLevel2;
            }
            case "Level 3" -> {
                level = "C";
                return parkingLevel3;
            }
            case "Level 4" -> {
                level = "D";
                return parkingLevel4;
            }
            default -> {
                level = "A";
                return parkingLevel1;
            }
        }
    }
}
