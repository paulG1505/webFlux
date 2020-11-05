package com.paul.webflux.WebFlux.dtos;
import com.paul.webflux.WebFlux.documents.User;


public class UserDto {
    private String loginId, name,type,user;
    private Boolean active;
    private  int[] data;

    public UserDto(){

    }

    public UserDto(String loginId, String name, String type, String user, Boolean active, int[] data){
        this.loginId=loginId;
        this.name=name;
        this.type=type;
        this.user=user;
        this.active=active;
        this.data=data;
    }
    public UserDto(User user){
        this.loginId=user.getLoginId();
        this.name=user.getName();
        this.type=user.getType();
        this.user=user.getUser();
        this.active=user.getActive();
        this.data=user.getData();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
