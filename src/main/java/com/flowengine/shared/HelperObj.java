package com.flowengine.shared;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class HelperObj {

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }


    public static UUID GenerateUUID()throws Exception
    {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();

        return uuid;
    }

}
