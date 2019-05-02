package com.example.proje;

import android.media.Image;

import java.io.Serializable;

public class Ogrenci implements Serializable {
    final public String kulTip="ögrenci";
    private String adSoyad;
    private String tCNo;
    private String pass;
    private String classNumber;
    private String emailAdres;
    private String yorum;
    private String loginId;
    private Image resim;

    public Image getResim() {
        return resim;
    }

    public void setResim(Image resim) {
        this.resim = resim;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String gettCNo() {
        return tCNo;
    }

    public void settCNo(String tCNo) {
        this.tCNo = tCNo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}
