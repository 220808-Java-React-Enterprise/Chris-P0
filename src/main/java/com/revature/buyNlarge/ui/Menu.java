package com.revature.buyNlarge.ui;
import java.util.*;

public interface Menu {
    //TODO go fix the readme
    public void display();

    public static String prompt(String promptMessage){
        return prompt(promptMessage, false);
    }
    public static String prompt(String promptMessage, boolean toLowerCase){
        return prompt(promptMessage, ".*", toLowerCase);
    }
    public static String prompt(String promptMessage, String option){
        return prompt(promptMessage, Arrays.asList(option), false);
    }
    public static String prompt(String promptMessage, String option, boolean toLowerCase){
        return prompt(promptMessage, Arrays.asList(option), toLowerCase);
    }
    public static String prompt(String promptMessage, List<String> options){
        return prompt(promptMessage, options, false);
    }
    public static String prompt(String promptMessage, List<String> options, boolean toLowerCase){
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        loop:
        while(true){
            System.out.print(promptMessage);
            userInput = scanner.nextLine();
            if(toLowerCase) {
                userInput = userInput.toLowerCase();
            }
            for(String option : options){
                if(userInput.matches(option)){
                    return userInput;
                }
            }
        }
    }
}

