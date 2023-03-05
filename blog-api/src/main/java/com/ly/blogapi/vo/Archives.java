package com.ly.blogapi.vo;

import lombok.Data;

/**
 * <p>
 *     归档视图对象
 * </p>
 *
 * @author zhuxuchen
 * @since 2023-03-05
 */
@Data
public class Archives {
    private Integer year;

    private Integer month;

    private Long count;
}
