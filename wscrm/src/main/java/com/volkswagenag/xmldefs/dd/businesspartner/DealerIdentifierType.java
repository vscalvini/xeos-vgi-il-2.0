//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.05.05 alle 10:59:22 AM CEST 
//


package com.volkswagenag.xmldefs.dd.businesspartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DealerIdentifierType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DealerIdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartnerKey" type="{http://xmldefs.volkswagenag.com/DD/BusinessPartner}PartnerKeyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DealerIdentifierType", propOrder = {
    "partnerKey"
})
public class DealerIdentifierType {

    @XmlElement(name = "PartnerKey")
    protected PartnerKeyType partnerKey;

    /**
     * Recupera il valore della proprietà partnerKey.
     * 
     * @return
     *     possible object is
     *     {@link PartnerKeyType }
     *     
     */
    public PartnerKeyType getPartnerKey() {
        return partnerKey;
    }

    /**
     * Imposta il valore della proprietà partnerKey.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerKeyType }
     *     
     */
    public void setPartnerKey(PartnerKeyType value) {
        this.partnerKey = value;
    }

}
