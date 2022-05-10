package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 作者：Rem
 * 时间：2021/12/22 1:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexData {
    private Integer currentPage;
    private Integer pageTotal;
    private Integer pageSize;
    private Integer total;
    private List<IndexList> list;

}
