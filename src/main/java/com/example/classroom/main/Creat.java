package com.example.classroom.main;

import cn.hutool.core.lang.Console;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.classroom.pojo.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 作者：Rem
 * 时间：2021/12/21 16:08
 */
@Component
public class Creat {


    public void creatExcel() {
         /*
        //构造请求json
        /*
         *   01=MDAwMDAwMDAwMLOctZSIubtohdtyoQ
         *   02=MDAwMDAwMDAwMLOctZWG379shdtyoQ
         *   03=MDAwMDAwMDAwMLOctZWH39GvhbVyoQ
         *   04=MDAwMDAwMDAwMLOctZaGubuyhKVyoQ
         *   05=MDAwMDAwMDAwMLOctZaHuaewhNtyoQ
         *   06=MDAwMDAwMDAwMLOctZaIuaexhrVyoQ
         *   07=MDAwMDAwMDAwMLOctZeGz6-vhrVyoQ
         * *-/
        //构建课堂id集合 （优化）
//        List<String> classIdList = new LinkedList<>();
//        classIdList.add("MDAwMDAwMDAwMLOctZSIubtohdtyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZWG379shdtyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZWH39GvhbVyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZaGubuyhKVyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZaHuaewhNtyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZaIuaexhrVyoQ");
//        classIdList.add("MDAwMDAwMDAwMLOctZeGz6-vhrVyoQ");

        //创建目录请求对象
        //{"courseid":"MDAwMDAwMDAwMLOGtd2IubexhLVyoQ","contenttype":4,"dirid":0,
        // "lessonlink":[],"sort":[],"page":1,"limit":100,"desc":3,"courserole":1,"reqtimestamp":1640085281141}
        */
        //课堂id 必填
        String courseId = "MDAwMDAwMDAwMLOGudyG37uyhctyoQ";
        //老师登录后获得的token 必填
        String token = "3dafc415238120c10a15058854f5f7aae8c1e4aa203c9b9f028fca02f8ed6cb4";

        //创建作业id集合
        List<String> classIdList = new LinkedList<>();
        //创建查询对象
        IndexSelect indexSelect = new IndexSelect();
        //配置参数，只需要更改课堂id
        indexSelect.setCourseid(courseId)
                .setContenttype(4)
                .setDirid(0)
                .setPage(1)
                .setLimit(100)
                .setDesc(3)
                .setCourserole(1);
        //获得作业目录数据
        IndexJson index = getIndex(indexSelect, token);
        //获得作业id列表
        List<IndexList> indexLists = index.getData().getList();
        for (int i = indexLists.size() - 1; i >= 0; i--) {
            //倒序添加
            classIdList.add(indexLists.get(i).getId());
        }

        //创建请求集合 方便循环访问每一次作业
        List<HomeworkSelect> homeworkSelectList = new LinkedList<>();
        //创建每一次作业对应学生分数的集合
        List<List<User>> homeworkList = new ArrayList<>();

        //遍历作业id 制作访问请求
        for (String id : classIdList) {

            for (int currentPage = 1; currentPage <= 2; currentPage++) {

                HomeworkSelect homeworkSelect = new HomeworkSelect();
                homeworkSelect.setHomeworkid(id)
                        .setPageSize(200)
                        .setCurrentPage(currentPage);
                homeworkSelectList.add(homeworkSelect);
            }

        }

        //制作好后循环访问，获得作业数据添加到分数集合
        for (HomeworkSelect select : homeworkSelectList) {
            //获取学生数据
            ReturnJson data = getData(select, token);

            List<User> userList = data.getData().getList();
            System.out.println("----->"+userList.size());
            homeworkList.add(userList);
        }


        //创建总map 存放所有学生的数据
        Map<String, List<String>> map = new HashMap<>();
        //遍历分数集合

        for (List<User> users : homeworkList) {
            for (User user : users) {

                String username = user.getUsername() + "---ID:" + user.getId();
                if (map.containsKey(username)) {
                    //如果有这个学生，就找到他，往他的分数列表里添加一个分数
                    List<String> gradeList = map.get(username);
                    gradeList.add(user.getGrade());
                } else {
                    //如果没有这个学生，就往map里面添加
                    List<String> list = new LinkedList<>();
                    list.add(user.getGrade());
                    map.put(username, list);
                }
            }
        }


        //初始化excel表格
        ArrayList<Map<String, Object>> rows = new ArrayList<>();

        //将map中的数据整理成表格 遍历
        map.forEach((key, value) -> {
            //因为map的key不能相同，所以成绩之间区分一下
            int count = 1;
            //创建一行
            Map<String, Object> row = new LinkedHashMap<>();
            //第一列存放username
            row.put("姓名", key.split("---ID:.*")[0]);
            //遍历value value是学生的成绩列表
            for (String grade : value) {
                //每有一个成绩 添加一列
                row.put("作业" + count, grade);
                count++;
            }
            //把这行加入总表
            rows.add(row);

        });
        System.out.println("rows---->"+rows.size());
        // 通过工具类创建writer
        String path = "d:/writeMapTest/" + new Date().getTime() + ".xlsx";
        ExcelWriter writer = ExcelUtil.getWriter(path);
        System.out.println(path);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(indexLists.size(), "十一班作业情况");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
    }

    public ReturnJson getData(HomeworkSelect homeworkSelect, String token) {
        String json = null;
        json = JSONObject.toJSONString(homeworkSelect);


        //发起请求
        HttpResponse res = HttpRequest.post("https://openapiv51.ketangpai.com/ReviewApi/getlistByhomework")
                .header("token", token)
                .body(json)
                .execute();
        String returnJson = UnicodeUtil.toString(res.body());
        //获得数据 封装为data
        ReturnJson data = JSON.parseObject(returnJson, ReturnJson.class);
        Console.log(data);
        return data;
    }

    /**
     * {"courseid":"MDAwMDAwMDAwMLOGtd2IubexhLVyoQ","contenttype":4,"dirid":0,"lessonlink":[],"sort":[],"page":1,"limit":100,"desc":3,"courserole":1,"reqtimestamp":1640085281141}
     */
    public IndexJson getIndex(IndexSelect indexSelect, String token) {
        String json = null;
        json = JSONObject.toJSONString(indexSelect);
        //发起请求
        HttpResponse res = HttpRequest.post("https://openapiv5.ketangpai.com//FutureV2/CourseMeans/getCourseContent")
                .header("token", token)
                .body(json)
                .execute();
        String returnJson = UnicodeUtil.toString(res.body());
        //获得数据 封装为data
        IndexJson data = JSON.parseObject(returnJson, IndexJson.class);
        Console.log(data);
        return data;
    }
}
