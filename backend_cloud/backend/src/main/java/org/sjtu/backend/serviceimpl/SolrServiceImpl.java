package org.sjtu.backend.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.service.SolrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {
    @Resource
    private SolrClient solrClient;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * search book whose info match the condition de
     * @param de
     * @return
     */
    @Override
    public List<Book> queryByCondition(String de) {
        List<Book> list = new ArrayList<>();
        // 按照关词de进行模糊查询
        SolrQuery query = new SolrQuery();
//        String nameLike = "book_name:*" + de + "*";
//        String authorLike = " OR book_author:*" + de + "*";
        String descriptionLike = "book_description:*" + de + "*";
//        query.set("q", nameLike + authorLike + descriptionLike);
        query.set("q", descriptionLike);

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

    /**
     * insert a valid new book into solr
     * @param book
     * @return
     */
    @Override
    public Map<String, Object> insertAndUpdate(Book book) {
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);

        // 插入或者更新solr数据
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", book.getId());
		document.setField("book_name", book.getName());
		document.setField("book_type", book.getType());
		document.setField("book_author", book.getAuthor());
		document.setField("book_description", book.getDescript());
        document.setField("book_inventory", book.getInventory());

        try {
            // 更新到solr，并在1秒内提交事务
			UpdateResponse response = solrClient.add(document,1000);
            int staus = response.getStatus();
            if (staus != 0) {
                logger.error(">>>> update solr document failed <<<<");
                solrClient.rollback();
                result.put("message","insert book to solr failed");
                return result;
            }
        } catch (SolrServerException e) {
            logger.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        }

        result.put("message","insert book to solr success");
        result.put("success",true);
        return result;
    }


    /**
     * delete the book in solr
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> deleteBookById(int id) {
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);

        try {
            UpdateResponse response = solrClient.deleteById(String.valueOf(id),1000);
            int status = response.getStatus();
            if (status != 0) {
                logger.error(">>>> delete book from solr failed ,user id=" + id + " <<<<");
                solrClient.rollback();
                result.put("message","delete book to solr failed");
                return result;
            }
        } catch (SolrServerException e) {
            logger.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            result.put("message",e.getMessage());
            return result;
        }

        result.put("success",true);
        result.put("message","delete book success");
        return result;
    }
}
