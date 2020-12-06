package com.connector.beta.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "images")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","user"})
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

    @Column(name = "size")
    private String size;

    @OneToOne(mappedBy = "image")
    @JsonIgnore
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageid=" + imageid +
                ", title='" + title + '\'' +
                ", file=" + Arrays.toString(file) +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", user=" + user +
                '}';
    }
}
