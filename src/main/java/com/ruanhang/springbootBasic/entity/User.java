package com.ruanhang.springbootBasic.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private Integer id;
    private String answer;
    private String card;
    private Integer expeGold;
    private Integer gameGold;
    private Integer level;
    private BigDecimal levelScore;
    private String loginDate;
    private String name;
    private String nickname;
    private String password;
    private String phone;
    private String question;
    private String registDate;
    private Integer sex;
    private Integer status;
    private String username;
    private Integer overflow;
    private Integer gameScore;
    private Integer photo_id;
    private Integer expeScore;
    private Integer lastDeskId;
    private Integer shutup_status;
    private Integer lastGame;
    private Integer type;
    private Integer expiryNum;
    private Integer payMoney;
    private Integer promoterId;
    private String promoterName;
    private String bindingName;
    private Integer lottery;
    private Integer safeBox;
    private Integer security;
    private String shareClearingTime;
    private Integer specialMark;
    private Integer subUserCount;
    private Integer warningStatus;
    private Integer boxGameGold;
    private Integer boxLottery;
    private Integer usercontrol;
}
