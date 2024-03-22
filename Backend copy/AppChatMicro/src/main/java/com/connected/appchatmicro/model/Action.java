package com.connected.appchatmicro.model;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
public class Action {

    @Id
    @GeneratedValue(strategy = IDENTITY)
   

    //
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fromUserId", referencedColumnName = "userId")
    private User fromUser;

    @Override
	public String toString() {
		return "Action [fromUser=" + fromUser + ", toUser=" + toUser + ", timeCreated=" + timeCreated + "]";
	}
	@ManyToOne(fetch = LAZY)
    @JoinColumn(name ="toUserId", referencedColumnName = "userId")
    private User toUser;

    private Instant timeCreated = Instant.now();

	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	public Instant getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Instant timeCreated) {
		this.timeCreated = timeCreated;
	}


}
