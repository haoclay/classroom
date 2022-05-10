package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：Rem
 * 时间：2021/12/22 1:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexJson {
    private String code;
    private IndexData data;
}
