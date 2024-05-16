package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Silence
 * @date: 2022/2/27 0:52
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    /**
     * 当前页
     */
    private long current;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 每页条数
     */
    private long size;
    /**
     * 总记录数
     */
    private long total;
}
