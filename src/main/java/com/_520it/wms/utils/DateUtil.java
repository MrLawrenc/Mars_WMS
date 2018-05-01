package com._520it.wms.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getBeginDate(Date beginDate) {//获取开始时间
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        //c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0); 和下面三行同效果
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Date getEndDate(Date endDate) {//获取开始时间
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
        c.add(Calendar.HOUR_OF_DAY, 1);
        c.add(Calendar.SECOND, -1);
        return c.getTime();
    }
}
