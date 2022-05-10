package com.example.classroom;

import com.example.classroom.main.Creat;
import com.example.classroom.pojo.IndexJson;
import com.example.classroom.pojo.IndexSelect;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ClassroomApplicationTests {
    @Resource
    private Creat creat;

    @Test
    void contextLoads() {
        creat.creatExcel();
    }

    @Test
    void indexTest(){
        //创建目录请求对象
        //{"courseid":"MDAwMDAwMDAwMLOGtd2IubexhLVyoQ","contenttype":4,"dirid":0,
        // "lessonlink":[],"sort":[],"page":1,"limit":100,"desc":3,"courserole":1,"reqtimestamp":1640085281141}
        IndexSelect indexSelect = new IndexSelect();
        indexSelect.setCourseid("MDAwMDAwMDAwMLOGtd2IubexhLVyoQ")
                .setContenttype(4)
                .setDirid(0)
                .setPage(1)
                .setLimit(100)
                .setDesc(3)
                .setCourserole(1);
        IndexJson index = creat.getIndex(indexSelect,"074aaa57eea016d241a3cb44ef7a902d699d06ef695e9c7a2306f682b9f40eca");
    }

}
