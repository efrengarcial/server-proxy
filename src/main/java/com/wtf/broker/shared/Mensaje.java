package com.wtf.broker.shared;

import java.io.Serializable;

/**
 * @author Efren
 */
public class Mensaje implements Serializable {
    
  	private static final long serialVersionUID = 9201330817172755147L;
	
	private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
