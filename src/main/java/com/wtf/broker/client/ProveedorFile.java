package com.wtf.broker.client;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map;

import com.wtf.broker.commons.Configuration;
import com.wtf.broker.util.CotizacionesUtil;
import com.wtf.quotation.domain.Cotizacion;
import com.wtf.quotation.domain.DetalleCotizacion;


/**
 *
 * @author edwinefl
 */
public class ProveedorFile {
    public static void main(String args[]) throws Exception {
        System.out.println("getCotizacionFile called");
        Cotizacion cotizacion=CotizacionesUtil.readFile(Configuration.FILEUBICATION);

        System.out.println("Identificación cotización: " + cotizacion.getIdCotizacion());
        System.out.println("Fecha cotizacion: " + cotizacion.getFechaCotizacion());
        
        System.out.println();
        System.out.println("idProveedor: " + cotizacion.getProveedor().getIdProveedor());
        System.out.println("Proveedor: " + cotizacion.getProveedor().getName());
        
         for (DetalleCotizacion detalle : cotizacion.getDetalles()) {
              System.out.println("Producto item: " + detalle.getItem().getIdItem());
              System.out.println("         descripción: " + detalle.getItem().getDesc());
              System.out.println("         referencia: " + detalle.getItem().getReferencia());
              System.out.println("         Categoria: " + detalle.getItem().getCategory());
         }
      }
}
