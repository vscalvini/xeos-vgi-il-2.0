package it.vwgroup.il.cap.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "T_CN_CAP")
public class Cap {
	
	@Id
	@Column(name="CAP", unique=true)
	private String cap;
	
	private String nation;
	private String province;
	private String provinceDescription;
	private String localityDescription;
	private String istatCode;
	
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceDescription() {
		return provinceDescription;
	}
	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}
	public String getLocalityDescription() {
		return localityDescription;
	}
	public void setLocalityDescription(String localityDescription) {
		this.localityDescription = localityDescription;
	}
	public String getIstatCode() {
		return istatCode;
	}
	public void setIstatCode(String istatCode) {
		this.istatCode = istatCode;
	}
	
}
