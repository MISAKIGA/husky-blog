package com.misakiga.husky.provider.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MISAKIGA
 */
@Data
@Table(name = "ums_permission")
public class UmsPermission implements Serializable {

    private static final long serialVersionUID = -6309784278282035821L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级权限id
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 前端资源路径
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    @Override
    public String toString() {
        return "UmsPermission{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", uri='" + uri + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", sort=" + sort +
                '}';
    }
}