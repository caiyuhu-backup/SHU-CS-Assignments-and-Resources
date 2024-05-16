package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.entity.MyFile;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/24 19:02
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFilePageVo extends Page {
    private List<MyFile> fileList;
}
