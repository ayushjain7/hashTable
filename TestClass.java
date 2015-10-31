import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by ayush on 10/8/15.
 */
public class TestClass {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Input the size of map you want to create.");
        String s = in.nextLine();
        HashCode hashCode = new HashCode(Integer.parseInt(s));
        System.out.println("What do you want to do? Enter 0 to exit");
        System.out.println("1. Insert value");
        System.out.println("2. Get value for a key");
        System.out.println("3. Delete key/value mapping");
        System.out.println("4. Load");
        s = in.nextLine();
        while(true){
            if(Pattern.matches("[0-9]+", s)){
                switch(Integer.parseInt(s)){
                    case (0):{
                        System.out.println("Quitting");
                        return;
                    }
                    case (1): {
                        System.out.println("Enter key and value separated by enter key");
                        String key = in.nextLine();
                        Object value = in.nextLine();
                        if(hashCode.set(key, value))
                            System.out.println("Operation successful");
                        else
                            System.out.println("Operation unsuccessful");
                        break;
                    }
                    case (2): {
                        System.out.println("Enter key to fetch value");
                        String key = in.nextLine();
                        System.out.println("The value for the key " + key + " is " + hashCode.get(key));
                        break;
                    }
                    case (3): {
                        System.out.println("Enter key to be deleted");
                        String key = in.nextLine();
                        if(hashCode.delete(key) == null)
                            System.out.println("Delete unsuccessful");
                        else
                            System.out.println("Delete successful");
                        break;
                    }
                    case (4): {
                        System.out.println("The load factor right now is " + hashCode.load());
                        break;
                    }
                    default:
                        System.out.println("Invalid input. Again");
                        break;
                }
            }else{
                System.out.println("Invalid Input. Try again");
            }
            System.out.println("Next Input");
            s = in.nextLine();
        }
    }
}