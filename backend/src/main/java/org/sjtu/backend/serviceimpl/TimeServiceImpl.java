package org.sjtu.backend.serviceimpl;

import org.sjtu.backend.service.TimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Scope("session")
public class TimeServiceImpl implements TimeService {

    private Date startTime;

    /**
     * 计时器开始计时
     */
    @Override
    public void startTimer() {
        startTime = new Date();
        return;
    }

    /**
     * 计时器停止计时，并返回过去的时间
     * @return
     */
    @Override
    public String endTimer() {
        Date endDate = new Date();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }
}
