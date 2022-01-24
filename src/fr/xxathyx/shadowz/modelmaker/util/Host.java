package fr.xxathyx.shadowz.modelmaker.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.xxathyx.shadowz.modelmaker.server.configuration.ServerConfiguration;

public class Host {

	public String server = "";
	public JsonObject object;
	
	public List<String> officials = Arrays.asList(new String[] { "AR", "DE", "ES", "FR", "GB", "IT", "RU", "TR" });
	
	public Host(String ip) {
		this.server = "http://ip-api.com/json/" + ip + "?fields=status,message,countryCode";
		object = new JsonParser().parse(websitedata(server)).getAsJsonObject();
	}
	
	public String getCOUNTRYCODE() {
		
		String countryCode = getObjectString("countryCode");
		
		if(!officials.contains(countryCode)) {
			return new ServerConfiguration().getServerLangage();
		}
		return getObjectString("countryCode");
	}
	
	public String getObjectString(String obj) {
		try {
			return object.get(obj).getAsString();
		} catch (Exception e) {
			return "Unknown";
		}
	}
	
	public List<String> getOfficials() {
		return officials;
	}
	
    public String websitedata(String website) {
        try {
            StringBuilder stringBuilder = new StringBuilder("");
            URL url = new URL(website);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}