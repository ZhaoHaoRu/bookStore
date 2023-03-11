package org.sjtu.backend.controller;

import org.sjtu.backend.serviceimpl.QueryService;
import org.sjtu.backend.utils.msgutils.Msg;
import org.sjtu.backend.utils.msgutils.MsgCode;
import org.sjtu.backend.utils.msgutils.MsgUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class QueryController {
    @Resource
    QueryService queryService;

    @RequestMapping("/queryAuthor")
    public Msg queryAuthor(@RequestParam("book_name") String book_name)  {
        String author = queryService.queryAuthor(book_name);
        return  MsgUtil.makeMsg(MsgUtil.SUCCESS, author);
    }
}
