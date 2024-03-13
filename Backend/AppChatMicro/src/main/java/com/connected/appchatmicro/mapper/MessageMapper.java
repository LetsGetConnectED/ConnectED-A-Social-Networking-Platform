package com.connected.appchatmicro.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.connected.appchatmicro.dto.MessageResponse;
import com.connected.appchatmicro.model.Message;
/*import com.connected.appchat.service.AuthService;*/
import com.github.marlonlom.utilities.timeago.TimeAgo;


@Mapper(componentModel = "spring")
public abstract class MessageMapper {

	
	/* @Autowired private AuthService authService; */
	 

    

    @Mapping(target = "messageId", source = "messageId")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "senderId", source = "senderId")
    @Mapping(target = "receiverId", source = "receiverId")
    @Mapping(target = "timeCreated", source = "timeCreated")
    @Mapping(target = "stringTimeCreated", expression= "java(getStringTimeCreated(msg))")
    @Mapping(target = "duration", expression = "java(getDuration(msg))")
    public abstract MessageResponse mapToDto(Message msg);

    String getDuration(Message msg) {
        return TimeAgo.using(msg.getTimeCreated().toEpochMilli());
    }

    String getStringTimeCreated(Message msg) {
        Date myDate = Date.from(msg.getTimeCreated());
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy HH:mm");
        String formattedDate = formatter.format(myDate);
        return formattedDate;
    }

}