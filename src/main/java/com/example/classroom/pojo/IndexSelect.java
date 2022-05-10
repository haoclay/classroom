package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作者：Rem
 * 时间：2021/12/22 2:00
 * {"courseid":"MDAwMDAwMDAwMLOGtd2IubexhLVyoQ","contenttype":4,"dirid":0,"lessonlink":[],"sort":[],"page":1,"limit":100,"desc":3,"courserole":1,"reqtimestamp":1640085281141}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class IndexSelect {
    //课程id
    private String courseid;
    private Integer contenttype;
    private Integer dirid;
    private Integer page;
    private Integer limit;
    private Integer desc;
    private Integer courserole;
    private Long reqtimestamp;
}
