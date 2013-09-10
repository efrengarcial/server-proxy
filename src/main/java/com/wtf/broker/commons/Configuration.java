package com.wtf.broker.commons;

import java.util.ResourceBundle;

public class Configuration {

	   private static ResourceBundle props = ResourceBundle.getBundle("configuration");
	   
	   public static final String TIPOCOMUNICACION = props.getString("TIPOCOMUNICACION");
	   public static final String FILEUBICATION = props.getString("FILEUBICATION");
	   public static final String ENPOINTADDRES = props.getString("ENPOINTADDRES");
	   
}
