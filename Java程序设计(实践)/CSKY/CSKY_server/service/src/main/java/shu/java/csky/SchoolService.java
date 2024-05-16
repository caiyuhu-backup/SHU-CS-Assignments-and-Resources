package shu.java.csky;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import shu.java.csky.entity.School;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.SchoolNameVo;
import shu.java.csky.vo.param.QueryPageParam;

import java.util.List;

/**
 * @author 20121706
 */
public interface SchoolService {
    ResultVO getSchoolVoList(Page<School> pageParam);

    School getSchoolDetailBySid(Integer sid);

    List<SchoolNameVo> getSchoolNameList();

    ResultVO fuzzyQuerySchool(QueryPageParam queryPageParam);
}
