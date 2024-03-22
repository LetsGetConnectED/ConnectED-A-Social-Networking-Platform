package com.connected.appchatmicro.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class PublicButton {
    public PublicButton() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PublicButton [publicbuttonId=" + publicbuttonId + ", userId=" + userId + ", work_exp=" + work_exp
				+ ", studies=" + studies + ", abilities=" + abilities + ", company=" + company + ", phone=" + phone
				+ "]";
	}

	public Long getPublicbuttonId() {
		return publicbuttonId;
	}

	public void setPublicbuttonId(Long publicbuttonId) {
		this.publicbuttonId = publicbuttonId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isWork_exp() {
		return work_exp;
	}

	public void setWork_exp(boolean work_exp) {
		this.work_exp = work_exp;
	}

	public boolean isStudies() {
		return studies;
	}

	public void setStudies(boolean studies) {
		this.studies = studies;
	}

	public boolean isAbilities() {
		return abilities;
	}

	public void setAbilities(boolean abilities) {
		this.abilities = abilities;
	}

	public boolean isCompany() {
		return company;
	}

	public void setCompany(boolean company) {
		this.company = company;
	}

	public boolean isPhone() {
		return phone;
	}

	public void setPhone(boolean phone) {
		this.phone = phone;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long publicbuttonId;

    private Long userId;
    private boolean work_exp;
    private boolean studies;
    private boolean abilities;
    private boolean company;
    private boolean phone;

    public PublicButton(Long userId,boolean work_exp, boolean studies, boolean abilities, boolean company, boolean phone) {
        this.userId = userId;
        this.work_exp = work_exp;
        this.studies = studies;
        this.abilities = abilities;
        this.company = company;
        this.phone = phone;
    }

    public PublicButton(Long userId) {
        this.userId = userId;
        this.work_exp = false;
        this.studies = true;
        this.abilities = true;
        this.company = false;
        this.phone = false;
    }
}
