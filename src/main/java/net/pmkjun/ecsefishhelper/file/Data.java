package net.pmkjun.ecsefishhelper.file;

import java.io.Serializable;
public class Data implements Serializable{
    public String userName;

    public String FishingMixinString="no data";
    public boolean toggleTotemtime = true;

    public boolean toggleTotemtimeText = true;

    public boolean isTotemCooldown = false;
    public int valueTotemCooldown = 1;
    public int valueTotemActivetime = 1;
    public double valueCooldownReduction = 5*1000;
    public long lastTotemTime=0;
    public long lastTotemCooldownTime = 0;
}
