package org.sjtu.backend.dao;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * 基础solr数据处理父类
 * @param <T>
 */
public class BaseSolrDao<T> {

    @Autowired
    protected SolrClient solrClient;//spring整合jar包提供的一个对象，可以进行注入

    public void insert(SolrInputDocument document) throws IOException, SolrServerException {
        solrClient.add(document);
        //提交
        solrClient.commit();
    }

    /**
     * 添加或修改，修改的时候先删除在修改避免数据重复
     * @param t
     */
    public void update(T t) throws IOException, SolrServerException {
        solrClient.addBean(t);
        //提交
        solrClient.commit();
    }

    /**
     * 删除
     * @param query
     * @return
     */
    public void delete(String query) throws IOException, SolrServerException {

        solrClient.deleteByQuery(query);
        //提交
        solrClient.commit();
    }

    /**
     * 查询
     * @param solrQuery
     * @param clazz
     * @return
     */
    public List<T> select(SolrQuery solrQuery, Class<T> clazz) throws IOException, SolrServerException {

        //查询
        QueryResponse queryResponse = solrClient.query(solrQuery);
        //将查询结果转换指定元素类型的集合
        List<T> list = queryResponse.getBeans(clazz);

        return list;

    }
}
