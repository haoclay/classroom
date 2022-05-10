package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 作者：Rem
 * 时间：2021/12/21 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class HomeworkSelect {
    //作业id MDAwMDAwMDAwMLOctZSIubtohdtyoQ"
    private String homeworkid;
    private String groupid;
    private String share;
    private String state;
    private String sim;
    private String words;
    private String rcount;
    private String sort;
    private String search;
    private Integer pageSize;//200  页面容量
    private Integer currentPage;//1 当前页
    private Long reqtimestamp;//1640075915819 时间戳

}
