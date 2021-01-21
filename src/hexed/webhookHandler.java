package hexed;

import arc.Core;
import arc.Net;
import arc.util.Log;
import arc.util.Strings;
import org.json.simple.JSONObject;

public class webhookHandler {
    public static void sendHexEndGame(String hexURL, Net net, String msg) {
        JSONObject reportJO = new JSONObject();
        reportJO.put("content","In:"+ Strings.stripColors(Core.settings.getString("servername"))+"\n"+msg);
        Net.HttpRequest request = (new Net.HttpRequest())
                .method(Net.HttpMethod.POST).header("Content-Type", "application/json")
                .content(reportJO.toJSONString()).url(hexURL);
        net.http(request,(s)->{
            Log.info("hex endgame sent. game is restarting");
        },(t)->{
            Log.info("hex endgame not sent. internet error.");
        });
    }
}
