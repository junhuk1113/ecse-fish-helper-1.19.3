package net.pmkjun.ecsefishhelper.file;

import java.io.Serializable;
public class Data implements Serializable{
    public String userName;

    public boolean toggleTotemtime = true;

    public boolean toggleTotemtimeText = true;

    public boolean isTotemCooldown = false;
    public int valueTotemCooldown = 60;
    public int valueTotemActivetime = 5;
    public long valueCooldownReduction = 0;
    public long lastTotemTime=0;
    public long lastTotemCooldownTime = 0;

    public Boolean isTimerright = false;
    public Boolean toggleCustomTexture = true;
    public Boolean toggleMuteotherfishingbobber = false;
    public int Timer_ypos = 1;
}
