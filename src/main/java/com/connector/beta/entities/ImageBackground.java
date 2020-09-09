package com.connector.beta.entities;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "image_background")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","user"})
public class ImageBackground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_background_id")
    private Integer imagebackgroundid;
    private String title;
    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private String size;

    @OneToOne(mappedBy = "imageBackground")
    private MyUser user;

    public ImageBackground() {
    }


    public Integer getImagebackgroundid() {
        return imagebackgroundid;
    }

    public void setImagebackgroundid(Integer imagebackgroundid) {
        this.imagebackgroundid = imagebackgroundid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }
}
