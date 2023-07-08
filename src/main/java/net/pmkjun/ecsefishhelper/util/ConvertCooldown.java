package net.pmkjun.ecsefishhelper.util;

public class ConvertCooldown {
    private ConvertCooldown(){

    }
    public static int asMinute(int level){
        return 60-(level*3);
    }
    public static int asLevel(int minute){
        return (60-minute)/3;
    }
}
