package com.example.qiaop.xiangmu_zhihu.beans.Greendaobeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Greendaolistbeans {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private boolean isBiaoshi;
    @Generated(hash = 145341111)
    public Greendaolistbeans(Long id, String name, boolean isBiaoshi) {
        this.id = id;
        this.name = name;
        this.isBiaoshi = isBiaoshi;
    }
    @Generated(hash = 1642039282)
    public Greendaolistbeans() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsBiaoshi() {
        return this.isBiaoshi;
    }
    public void setIsBiaoshi(boolean isBiaoshi) {
        this.isBiaoshi = isBiaoshi;
    }

    @Override
    public String toString() {
        return "Greendaolistbeans{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isBiaoshi=" + isBiaoshi +
                '}';
    }
}
