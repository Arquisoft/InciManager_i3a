package org.uniovi.i3a.incimanager.kafka;

import java.util.List;
import java.util.Map;

public class IncidenceJsonFormatter {

	public static String jsonFormatter(Map<String, Object> payload) {
		String username = payload.get("username").toString();
		String password = payload.get("password").toString();
		String incidenceName = payload.get("incidenceName").toString();
		String description = payload.get("description").toString();
		String asignee = payload.get("asignee").toString();
		Long expiration = (Long) payload.get("expiration");
		String state = payload.get("state").toString();
		String location = payload.get("location").toString();
		List<String> tags = (List<String>) payload.get("tags");
		List<String> additionalInfo = (List<String>) payload.get("additional_information");
		Map<String, String> properties = (Map<String, String>) payload.get("properties");

		String json = "{" + "'username':'" + username + "'," + "'password':'" + password + "'," + "'incidence_name':'"
				+ incidenceName + "'," + "'description':'" + description + "'," + "'asignee':'" + asignee + "',"
				+ "'expiration':'" + expiration + "'," + "'state':'" + state + "'," + "'location':'" + location + "',"
				+ "'tags':%s," + "'additional_information':%s," + "'properties':%s" + "}";

		String tagsJson = "[";
		for (String s : tags) {
			tagsJson += "'" + s + "',";
		}
		tagsJson = tagsJson.substring(0, tagsJson.length() - 1);
		tagsJson += "]";

		String additionalInfoJson = "[";
		for (String s : additionalInfo) {
			additionalInfoJson += "'" + s + "',";
		}
		additionalInfoJson = additionalInfoJson.substring(0, additionalInfoJson.length() - 1);
		additionalInfoJson += "]";

		String propertiesJson = "{";
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			propertiesJson += "'" + entry.getKey() + "':'" + entry.getValue() + "',";
		}
		propertiesJson = propertiesJson.substring(0, propertiesJson.length() - 1);
		propertiesJson += "}";

		json = String.format(json, tagsJson, additionalInfoJson, propertiesJson);

		return json;
	}
}
