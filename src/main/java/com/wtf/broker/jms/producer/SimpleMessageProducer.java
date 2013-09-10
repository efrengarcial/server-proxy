package com.wtf.broker.jms.producer;

import java.net.MalformedURLException;
import java.net.URL;

import javax.jms.JMSException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.wtf.broker.client.ICotizaciones;
import com.wtf.broker.commons.Configuration;
import com.wtf.broker.shared.Mensaje;
import com.wtf.broker.util.CotizacionesUtil;
import com.wtf.quotation.domain.Cotizacion;
import com.wtf.quotation.domain.DetalleCotizacion;
import com.wtf.quotation.domain.SolicitudCotizacion;

public class SimpleMessageProducer {
    
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);
    private static final QName SERVICE_NAME = new QName("http://sever.cotizacion.proveedor.wtf.com/", "ICotizaciones");
    private static final QName PORT_NAME = new QName("http://sever.cotizacion.proveedor.wtf.com/", "CotizacionesImpl");

    protected JmsTemplate jmsTemplate; 
    
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void sendMessages(SolicitudCotizacion solicitud) throws JMSException {
		 //final StringBuilder buffer = new StringBuilder(); 
		Cotizacion cotizacion=null;
		if(Configuration.TIPOCOMUNICACION.equals("FILE")){
			cotizacion=CotizacionesUtil.readFile(Configuration.FILEUBICATION);
			cotizacion.setSolicitud(solicitud);
		}else if (Configuration.TIPOCOMUNICACION.equals("SOA")){
			
			// Endpoint Address
	        String endpointAddress = Configuration.ENPOINTADDRES;
	        URL wsdllocation=null;
			try {
				wsdllocation = new URL(endpointAddress);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
	        Service service = Service.create(wsdllocation, SERVICE_NAME);
	        // Add a port to the Service
	        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
	        
	        ICotizaciones hw = service.getPort(ICotizaciones.class);
	        cotizacion= hw.getCotizaciones();
		}

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
        //se envia el mensaje
		jmsTemplate.convertAndSend(cotizacion);
		LOG.info("enviando mensaje cola QCotizaciones....");      
    }
}
