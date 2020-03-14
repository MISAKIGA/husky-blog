package com.misakiga.husky.provider.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "ums_role")
public class UmsRole implements Serializable {


    private static final long serialVersionUID = -7323310959640396397L;
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 后台用户数量
     */
    @Column(name = "admin_count")
    private Integer adminCount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @Column(name = "`status`")
    private Integer status;

    @Column(name = "sort")
    private Integer sort;


    @Override
    public String toString() {
        return "UmsRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adminCount=" + adminCount +
                ", createTime=" + createTime +
                ", status=" + status +
                ", sort=" + sort +
                '}';
    }
}