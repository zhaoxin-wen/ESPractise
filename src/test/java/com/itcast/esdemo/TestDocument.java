package com.itcast.esdemo;



import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author wzw
 * @date 2023/11/4 14:10
 * @description
 */
@SpringBootTest(classes = ESdemoApplication.class)
@RunWith(SpringRunner.class)
public class TestDocument {
    @Autowired
    RestHighLevelClient client;

//    @Test
//    public void testGet() throws IOException {
//        //1.构建请求
//        GetRequest getRequest = new GetRequest("test_post", "1");
//        //可选参数
////        String[] include = Strings.EMPTY_ARRAY;
////        String[] exclude = new String[]{"user","message"};
////        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,include,exclude);
////        getRequest.fetchSourceContext(fetchSourceContext);
////        getRequest.routing("routing");
//
//
//        //2.执行
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
////        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
////            @Override
////            public void onResponse(GetResponse getResponse) {
////                System.out.println(getResponse.getId());
////                System.out.println(getResponse.getVersion());
////                System.out.println(getResponse.getSourceAsString());
////            }
////
////            @Override
////            public void onFailure(Exception e) {
////                e.printStackTrace();
////            }
////        };
////        client.getAsync(getRequest, RequestOptions.DEFAULT,listener);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        //3.获取结果
//        System.out.println(getResponse.getId());
//        System.out.println(getResponse.getVersion());
//        System.out.println(getResponse.getSourceAsString());
//    }

    @Test
    public void testGet() throws IOException {
//      1构建请求
        GetRequest getRequest = new GetRequest("test_post", "1");
//        异步查询
        ActionListener<GetResponse> listener=new ActionListener<GetResponse>() {
            //成功时
            public void onResponse(GetResponse getResponse) {
                System.out.println(getResponse.getId());
                System.out.println(getResponse.getVersion());
                System.out.println(getResponse.getSourceAsString());
                System.out.println("I am in succes");
            }
            //失败时
            public void onFailure(Exception e) {
                System.out.println("I am in exception");
                e.printStackTrace();
            }
        };
        client.getAsync(getRequest, RequestOptions.DEFAULT, listener);

        System.out.println("test----------------------------------test");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
