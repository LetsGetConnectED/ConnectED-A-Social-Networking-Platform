package com.connected.appchatmicro.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class Personalinfo {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long infoId;
//    private Long userId;
	private String work_desc = "";
	private String stud_desc = "";
	private String abilities_desc = "";

	@OneToOne(fetch = LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "userId", referencedColumnName = "userId")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	public Personalinfo(User u, String work_desc, String stud_desc, String abilities_desc) {
//        this.userId = userId;
		this.user = u;
		this.work_desc = work_desc;
		this.stud_desc = stud_desc;
		this.abilities_desc = abilities_desc;
	}

	public Personalinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Personalinfo [infoId=" + infoId + ", work_desc=" + work_desc + ", stud_desc=" + stud_desc
				+ ", abilities_desc=" + abilities_desc + ", user=" + user + "]";
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getWork_desc() {
		return work_desc;
	}

	public void setWork_desc(String work_desc) {
		this.work_desc = work_desc;
	}

	public String getStud_desc() {
		return stud_desc;
	}

	public void setStud_desc(String stud_desc) {
		this.stud_desc = stud_desc;
	}

	public String getAbilities_desc() {
		return abilities_desc;
	}

	public void setAbilities_desc(String abilities_desc) {
		this.abilities_desc = abilities_desc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Personalinfo(User user) {
		this.user = user;
	}
}
