package me.shib.java.omdb.service;

import me.shib.java.rest.client.lib.JsonLib;

public class StaticLibrary {
	
	private static JsonLib jsonLib;
	
	public static JsonLib getJsonLib() {
		if(jsonLib == null) {
			jsonLib = new JsonLib();
		}
		return jsonLib;
	}
	
}
