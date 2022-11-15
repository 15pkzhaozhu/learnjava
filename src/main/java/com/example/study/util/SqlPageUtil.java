package com.example.study.util;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Arrays;
import java.util.List;

public final class SqlPageUtil {
    /**
     * 手工处理分页信息，返回List，并设置page相关属性
     *
     * @param page
     * @param collection
     * @param <T>
     * @return
     */
    public static <T> List<T> pageHandler(Page<T> page, List<T> collection) {
        int total = collection.size();
        int pageEndIndex = getPageEndIndex(page);
        List<T> subList = collection.subList(getPageStartIndex(page), Math.min(pageEndIndex, total));
        page.setTotal(total);
        return subList;
    }

    /**
     * 获取page结束下标  仅用于容器分页，不能用于数据库分页
     *
     * @param page
     * @return
     */
    public static int getPageEndIndex(Page<?> page) {

        return (int) (page.getCurrent() * page.getSize());
    }

    /**
     * 获取page起始下标  仅用于容器分页，不能用于数据库分页
     *
     * @param page
     * @return
     */
    public static int getPageStartIndex(Page<?> page) {
        long start = (page.getCurrent() - 1) * page.getSize();
        return Math.max((int) start, 0);
    }

    public static void main(String[] args) {
        Page<String> page = new Page((long)(1 + 1), (long)3);
        List<String> collection = Arrays.asList("1","2","3","4","5","6","7","8","9","10");
        List<String> result = SqlPageUtil.pageHandler(page, collection);
        System.out.println(result);
    }
}