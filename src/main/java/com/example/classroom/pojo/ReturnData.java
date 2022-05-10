package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 作者：Rem
 * 时间：2021/12/21 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnData {
    private Integer total;
    private List<User> list;
}
