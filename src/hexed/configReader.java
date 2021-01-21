package hexed;

import arc.util.Log;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class configReader {
    final static String userHomePath ="config/mods";
    public static String make(String fileName, JSONObject object) {
        try {
            //String userHomePath = "config/mods";//System.getProperty("config/mods/user.home");
            //System.getProperty(user.home)
            //Log.info("user.dir="+System.getProperty("user.dir"));
            //Log.info("user.home"+System.getProperty("user.home"));
            //Log.info(System.getProperty("user.dir"));
            File file = new File(userHomePath+"/"+fileName+".alex");
            File path = new File(userHomePath+"/");
            if (!path.isDirectory()) {
                Log.err("404 - could not find directory "+userHomePath+"/");
                return null;
            }
            if (!file.exists()) file.createNewFile();
            FileWriter out = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(out);
            pw.println(object.toString(4));
            out.close();
            return "Done";
        } catch (IOException i) {
            i.printStackTrace();
            return "error: \n```"+i.getMessage()+"\n```";
        }
    }
    public static JSONObject get(String fileName) {
        try {
            //String userHomePath = System.getProperty("user.home");
            File file = new File(userHomePath+"/"+fileName+".alex");
            File path = new File(userHomePath+"/");
            if (!path.isDirectory()) {
                Log.err("404 - could not find directory "+userHomePath+"/");
                return null;
            }
            if (!file.exists()) {
                Log.err("404 - "+userHomePath+"/"+fileName+".alex"+" not found");
                return null;
            }
            FileReader fr = new FileReader(file);
            StringBuilder builder = new StringBuilder();
            int i;
            while((i=fr.read())!=-1) {
                builder.append((char)i);
            }
            //return null;
            return new JSONObject(new JSONTokener(builder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String putStr(String fileName, String key, String value) {
        try {
            JSONObject data = get(fileName);
            if (data == null) return null;
            if (!value.equals("")) {
                data.put(key, value);
            } else {
                return "Error - value == \"\" and valueNumber == 0";
            }
            return save(fileName, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String save(String fileName, JSONObject object) {
        //String userHomePath = System.getProperty("user.home");
        File file = new File(userHomePath+"/"+fileName+".alex");
        File path = new File(userHomePath+"/");
        if (!path.isDirectory()) {
            Log.err("404 - could not find directory "+userHomePath+"/");
            return null;
        }
        if (!file.exists()) {
            Log.err("404 - "+userHomePath+"/"+fileName+".alex"+" not found");
            return null;
        }
        try {
            FileWriter out = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(out);
            pw.println(object.toString(4));
            out.close();
            return "Done";
        } catch (IOException it) {
            it.printStackTrace();
            return "error: \n```"+it.getMessage()+"\n```";
        }
    }
}
