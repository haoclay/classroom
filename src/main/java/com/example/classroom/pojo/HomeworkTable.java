package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 作者：Rem
 * 时间：2021/12/21 18:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkTable {
    private String username;
    private List<String> gradeLIst;
}
