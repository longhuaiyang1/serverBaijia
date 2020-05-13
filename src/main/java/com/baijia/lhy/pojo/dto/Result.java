package com.baijia.lhy.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Result implements Serializable {
    int code; //200~300之间表示正确，否则表示错误
    String msg;
    Object data;
}
