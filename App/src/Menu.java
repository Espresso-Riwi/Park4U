import javax.swing.*;
import java.util.HashMap;

public class Menu {
    public void menu(){
        HashMap<String, HashMap<String, String>> parkingLevel1 = new HashMap<>();
        Entrance e = new Entrance();
        String[] options = {"Entrance", "Membership", "Agreement", "Out","Exit"};
        String option = JOptionPane.showInputDialog(null, "Welcome to Park4U\nChoose the option that you want: ", "Menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option){
            case "Entrance":
                e.entrance(parkingLevel1);
                break;
            case "Membership":
                break;
            case "Agreement":
                break;
            case "Out":
                break;
            case "Exit":
                break;
        }

    }
}
