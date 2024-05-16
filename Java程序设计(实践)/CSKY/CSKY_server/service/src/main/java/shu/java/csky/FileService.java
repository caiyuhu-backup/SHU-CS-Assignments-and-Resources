package shu.java.csky;

import com.baomidou.mybatisplus.core.metadata.IPage;
import shu.java.csky.entity.MyFile;
import shu.java.csky.vo.ResultVO;

/**
 * @author: Silence
 * @date: 2022/2/24 18:41
 * @description:
 */
public interface FileService {
    ResultVO getFilePageBySubjectCode(String subject, IPage<MyFile> page);
}
