package com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaocollect {
    @Id(autoincrement = true)
    Long id;
    String title;
    String url;
    String img;
    String name;
    int dataid;
    boolean isCollect;
    @Generated(hash = 1683891157)
    public GreenDaocollect(Long id, String title, String url, String img,
            String name, int dataid, boolean isCollect) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.img = img;
        this.name = name;
        this.dataid = dataid;
        this.isCollect = isCollect;
    }
    @Generated(hash = 2006917949)
    public GreenDaocollect() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDataid() {
        return this.dataid;
    }
    public void setDataid(int dataid) {
        this.dataid = dataid;
    }
    public boolean getIsCollect() {
        return this.isCollect;
    }
    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    @Override
    public String toString() {
        return "GreenDaocollect{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", dataid=" + dataid +
                ", isCollect=" + isCollect +
                '}';
    }
}
