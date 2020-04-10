package lt.codeacademy.inputs;

import java.util.Scanner;

public class Inputs {
    Scanner scanner =new Scanner(System.in);

    public String stringInput(){
        return scanner.next();
    }

    public String stringNextLine(){
        return scanner.nextLine();
    }
    public int intInput(){
        return scanner.nextInt();
    }

}
