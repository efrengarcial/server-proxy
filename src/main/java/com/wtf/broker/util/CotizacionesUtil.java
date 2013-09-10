package com.wtf.broker.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.wtf.quotation.domain.Cotizacion;
import com.wtf.quotation.domain.DetalleCotizacion;
import com.wtf.quotation.domain.Item;
import com.wtf.quotation.domain.Proveedor;
/**
 * 
 * @author edwinefl
 */
public class CotizacionesUtil {
	public static Cotizacion readFile(String configurationFilePath) {
		Cotizacion cotizacion = null;
		try {
			// Abrimos el archivo
			FileInputStream fstream = new FileInputStream(configurationFilePath);
			// Creamos el objeto de entrada
			DataInputStream entrada = new DataInputStream(fstream);
			// Creamos el Buffer de Lectura
			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					entrada));
			String strLinea;
			// Leemos el encabezado
			strLinea = buffer.readLine();
			// Leemos el encabezado el encabezado
			cotizacion = llenarCotizacion(cotizacion, (String[]) strLinea.split(";"));

			ArrayList<DetalleCotizacion> detalleCotizacion = new ArrayList<DetalleCotizacion>();
			// Leer el archivo linea por linea
			while ((strLinea = buffer.readLine()) != null) {
				detalleCotizacion.add(llenarDetalle(cotizacion,(String[]) strLinea.split(";")));
			}
			
			cotizacion.setDetalles(detalleCotizacion);
			
			// Cerramos el archivo
			entrada.close();
		} catch (Exception e) { // Catch de excepciones
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
		return cotizacion;
	}

	public static Cotizacion  llenarCotizacion(Cotizacion cotizacion, String[] linea) {
		
		if (linea != null && linea.length > 0) {
			Proveedor proveedor = new Proveedor(Integer.parseInt(linea[0]),linea[1]);
			cotizacion = new Cotizacion(Integer.parseInt(linea[2]), proveedor);
			Calendar date = Calendar.getInstance();
			String[]fecha=linea[3].split("/");
			date.set(Calendar.YEAR, Integer.valueOf(fecha[2]));
			date.set(Calendar.MONTH, Integer.valueOf(fecha[1]));
			date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(fecha[0]));
			cotizacion.setFechaCotizacion(date);
		}
		return cotizacion;
	}

	public static DetalleCotizacion llenarDetalle(Cotizacion cotizacion,String[] linea) {
		if (linea != null && linea.length > 0) {
			Item item=new Item(Integer.parseInt(linea[1]),linea[2],Integer.parseInt(linea[3]),linea[4]);
			DetalleCotizacion detalle = new DetalleCotizacion(Integer.parseInt(linea[0]), cotizacion, item, Float.parseFloat(linea[5]));
			return detalle;
		}
		return null;
	}

}
