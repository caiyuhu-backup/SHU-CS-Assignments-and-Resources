package shu.java.csky.vo.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.Comment;
import shu.java.csky.entity.MyFile;

/**
 * @author: Silence
 * @date: 2022/2/24 19:20
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFilePageParam {
    private Integer code;
    private Page<MyFile> page;
}
