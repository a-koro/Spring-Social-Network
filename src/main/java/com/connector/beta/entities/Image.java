package com.connector.beta.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "images")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;
    private String title;
    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "type")
    private String type;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="user_image",
            joinColumns = @JoinColumn(name="imageid"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private MyUser user;

    public Image() {
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
