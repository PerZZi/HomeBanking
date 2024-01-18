package com.MindHub.HomeBanking.utils;

public class Utils {


    public static String getCardNumber(){
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i<4; i++){
            int sectionDigits = (int) (Math.random() * 9000 + 1000);
            cardNumber.append(sectionDigits).append("-");
        }
        return cardNumber.substring(0,cardNumber.length()-1);
    }

    public static int getAccountNumber(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String numberAccount (){
        String number;
        number = "VIN" + getAccountNumber(00000000,99999999);
        return number.toString();
    }

    public static int GeneratedCardCvv(){
        int cvv = (int)(Math.random() * 899 + 100);
        return cvv;
    }
}
