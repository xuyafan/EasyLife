package com.github.xuyafan.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author： xuyafan
 * description:
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long id = 0;
    private String name = null;
    private String avatar = null;
    private String gender = null;
    private String address = null;

    @Generated(hash = 851262146)
    public UserProfile(long id, String name, String avatar, String gender,
                       String address) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
    }

    @Generated(hash = 968487393)
    public UserProfile() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
