package frc.robot;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.*;
//import com.ctre.phoenix.CANifier.GeneralPin;


public class Canifier{
    CANifier canifier = new CANifier(0);
    int pin1 = 8;
    int pin2 = 7;
    int pin3 = 6;
    int pin4 = 5;
    int pin5 = 4;
    int pin6 = 3;

    int pin7 = 2;
    int pin8 = 1;

    int pinArray[] = {pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8};

    boolean dataBuf[] = {};

    public boolean getPinData()[]{
        for(int i = 0; i < 8; i++){
            dataBuf[i] = canifier.getGeneralInput(GeneralPin.valueOf(pinArray[i]));	
        }
        return dataBuf;
    }

    public int binToDecColor(boolean arr[]){
        int intBuf = 0;

        for(int ii = 0; ii < 6; ii++){
            if(arr[ii]){
                intBuf += 2^ii;
            }
        }
        return intBuf;

    }
    public int binToDecCount(boolean arr[]){
        int countBuf = 0;
        for(int ii = 6; ii < 8; ii++){
            if(arr[ii]){
                countBuf += 2^ii;
            }
        }
        return countBuf;
    }

}