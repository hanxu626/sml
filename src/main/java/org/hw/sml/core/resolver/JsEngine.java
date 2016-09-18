package org.hw.sml.core.resolver;

import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsEngine {
	public static Logger logger=LoggerFactory.getLogger(JsEngine.class);
	private static ScriptEngine engine;
	static{
		 ScriptEngineManager manager = new ScriptEngineManager();
		 engine = manager.getEngineByName("javascript");
		 try {
			engine.eval(new InputStreamReader(JsEngine.class.getResourceAsStream("js_time_format.js")));
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	public static boolean evelBoolean(String msg) throws ScriptException{
			return Boolean.valueOf(String.valueOf(engine.eval(msg)));
	}
	public static Object  evelNil(String msg){
		try {
			return engine.eval(msg);
		} catch (Throwable e) {
			//logger.error("msg[{}]",msg);
		}
		return "";
	}
	public static Object  evel(String msg){
		try {
			return engine.eval(msg);
		} catch (ScriptException e) {
			e.printStackTrace();
			logger.error("msg[{}]",msg);
		}
		return null;
	}
	public static void main(String[] args) throws ScriptException {
		JsEngine.engine.put("aa","12");
		//JsEngine.engine.pu
		System.out.println(evel("aa>=12"));
		System.out.println(evel("('a,a,b,a'.split(',').length-1)==3"));
		System.out.println(evel(" ['a','b','c'].in_array('a')"));
	}
}
