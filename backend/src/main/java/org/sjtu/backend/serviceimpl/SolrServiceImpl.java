package org.sjtu.backend.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.service.SolrService;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {
    @Resource
    private SolrClient solrClient;


    @Override
    public List<Book> queryByCondition(String de) {
        List<Book> list = new ArrayList<>();
        // 按照关词de进行模糊查询
        SolrQuery query = new SolrQuery();
        String nameLike = "book_name:*" + de + "*";
        String authorLike = " OR book_author:*" + de + "*";
        String descriptionLike = " OR book_description:*" + de + "*";
        query.set("q", nameLike + authorLike + descriptionLike);

        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList documentList = response.getResults();

            if (!documentList.isEmpty()) {
                Gson gson = new Gson();
                String listString = gson.toJson(documentList);
                JSONArray jArray = JSONArray.parseArray(listString);

                // 解析 jArray
                if (jArray != null) {
                    for(int i = 0; i < jArray.size(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);

                        Book new_Book = new Book(jsonObject.getString("id"), jsonObject.getString("book_name"),
                                jsonObject.getString("book_type"), jsonObject.getString("book_author"),
                                jsonObject.getString("book_description"), jsonObject.getInteger("book_inventory"));

                        // 如果是已经失效的书，不加入
                        if(new_Book.getInventory() > 0) {
                            list.add(new_Book);
                        }
                    }
                }
            } else {
                System.out.println(">>>> no result returned by the filter query word: " + de + " <<<<");
            }

        } catch (SolrServerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}
