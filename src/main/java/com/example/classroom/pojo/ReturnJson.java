package com.example.classroom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者：Rem
 * 时间：2021/12/21 17:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnJson {
    private Integer status;
    private Integer code;
    private ReturnData data;

}
