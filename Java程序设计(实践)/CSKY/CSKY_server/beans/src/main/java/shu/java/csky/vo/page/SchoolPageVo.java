package shu.java.csky.vo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shu.java.csky.vo.SchoolVo;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/20 9:34
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolPageVo extends Page{
    List<SchoolVo> schoolVoList;
}
