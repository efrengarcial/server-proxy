package com.wtf.broker.client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wtf.quotation.domain.Cotizacion;


/**
 *
 * @author edwinefl
 */
@WebService
public interface ICotizaciones {
    
	Cotizacion getCotizaciones();
    
}
