package com.revature.buyNlarge.ui;
import java.util.*;

public interface Menu {
    public void display();

    public static String prompt(String promptMessage){
        return prompt(promptMessage, ".*");
    }
    public static String prompt(String promptMessage, String option){
        return prompt(promptMessage, Arrays.asList(option));
    }
    public static String prompt(String promptMessage, List<String> options){
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        loop:
        while(true){
            System.out.print(promptMessage);
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase();
            for(String option : options){
                if(userInput.matches(option)){
                    return userInput;
                }
            }
        }
    }
}

