package com.dxc.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="candidate")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  long  recid;
	private  long candidateno;
    private  String candidatename;
    private  long score;
    private  int rank;
    private Date updatedate;
	public long getRecid() {
		return recid;
	}
	public void setRecid(long recid) {
		this.recid = recid;
	}
	public long getCandidateno() {
		return candidateno;
	}
	public void setCandidateno(long candidateno) {
		this.candidateno = candidateno;
	}
	public String getCandidatename() {
		return candidatename;
	}
	public void setCandidatename(String candidatename) {
		this.candidatename = candidatename;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "Candidate [recid=" + recid + ", candidateno=" + candidateno + ", candidatename=" + candidatename
				+ ", score=" + score + ", rank=" + rank + ", updatedate=" + updatedate + "]";
	}
	public Candidate(long recid, long candidateno, String candidatename, long score, int rank, java.sql.Date date) {
		super();
		this.recid = recid;
		this.candidateno = candidateno;
		this.candidatename = candidatename;
		this.score = score;
		this.rank = rank;
		this.updatedate =  date;
	}
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}



