package cn.edu.sjtu.ipads;


import com.sun.scenario.effect.InvertMask;

import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/27 19:50
 */
public class Customer {
    private String customerId;
    private String nickname;
    private Gender gender;
    private Integer age;
    private String address;
    private Integer level;
    private Integer currentSP;

    public Customer() {
    }

    public synchronized void addSp(int value) {
        this.currentSP += value;
        int levelUpSP = LevelUPValue.VALUES[level];
        if (currentSP >= levelUpSP) {
            currentSP -= levelUpSP;
            this.level++;
        }
    }

    public Customer(Map<String, Object> map) {
        this.customerId = (String) map.get("customerId");
        this.nickname = (String) map.get("nickname");
        this.gender = Gender.valueOf((String) map.get("gender"));
        this.age = (Integer) map.get("age");
        this.address = (String) map.get("address");
        this.level = (Integer) map.get("level");
        this.currentSP = (Integer) map.get("currentSP");
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCurrentSP() {
        return currentSP;
    }

    public void setCurrentSP(Integer currentSP) {
        this.currentSP = currentSP;
    }


}
