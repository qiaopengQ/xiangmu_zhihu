package com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaoBiaoshi {
    @Id(autoincrement = true)
    Long id;
    boolean isCache;
    boolean isNoimage;
    boolean isNight;
    @Generated(hash = 1310641065)
    public GreenDaoBiaoshi(Long id, boolean isCache, boolean isNoimage,
            boolean isNight) {
        this.id = id;
        this.isCache = isCache;
        this.isNoimage = isNoimage;
        this.isNight = isNight;
    }
    @Generated(hash = 1175143239)
    public GreenDaoBiaoshi() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsCache() {
        return this.isCache;
    }
    public void setIsCache(boolean isCache) {
        this.isCache = isCache;
    }
    public boolean getIsNoimage() {
        return this.isNoimage;
    }
    public void setIsNoimage(boolean isNoimage) {
        this.isNoimage = isNoimage;
    }
    public boolean getIsNight() {
        return this.isNight;
    }
    public void setIsNight(boolean isNight) {
        this.isNight = isNight;
    }

    @Override
    public String toString() {
        return "GreenDaoBiaoshi{" +
                "id=" + id +
                ", isCache=" + isCache +
                ", isNoimage=" + isNoimage +
                ", isNight=" + isNight +
                '}';
    }
}
