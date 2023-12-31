package net.pmkjun.ecsefishhelper.config;

import net.pmkjun.ecsefishhelper.FishHelperClient;
import net.pmkjun.ecsefishhelper.file.Data;

import java.io.*;

public class ConfigManage {
    private static final String DATA_DIRECTORY_PATH = "\\ECSE FishHelper";

    private static final String DATA_FILE_PATH = "\\ECSE FishHelper\\configv2.data";

    public void save() {
        save((FishHelperClient.getInstance()).data);
    }

    public void save(Data data) {
        File file = new File(System.getProperty("user.dir") + DATA_FILE_PATH);
        File directory = new File(System.getProperty("user.dir") + DATA_DIRECTORY_PATH);
        try {
            if (!file.exists()) {
                directory.mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Data load() {
        File file = new File(System.getProperty("user.dir") + DATA_FILE_PATH);
        try {
            if (!file.exists())
                save(new Data());
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Data data = (Data)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
