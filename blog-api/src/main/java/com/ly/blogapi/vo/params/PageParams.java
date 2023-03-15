package com.ly.blogapi.vo.params;

import lombok.Data;

/**
 * <p>
 *     页面参数
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-03
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
    private Long categoryId;
    private Long tagId;
    private String year;
    private String month;
    //传递6的话变成06

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
