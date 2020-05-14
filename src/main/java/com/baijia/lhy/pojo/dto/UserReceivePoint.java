package com.baijia.lhy.pojo.dto;

import com.baijia.lhy.pojo.entity.ReceivePoint;
import com.baijia.lhy.pojo.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserReceivePoint implements Serializable {
    
    private User user;

    private ReceivePoint receivePoint;
}
