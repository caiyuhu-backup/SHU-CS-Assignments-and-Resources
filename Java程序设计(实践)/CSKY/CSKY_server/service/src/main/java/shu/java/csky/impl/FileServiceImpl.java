package shu.java.csky.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import shu.java.csky.FileService;
import shu.java.csky.dao.MyFileMapper;
import shu.java.csky.entity.MyFile;
import shu.java.csky.vo.ResStatus;
import shu.java.csky.vo.ResultVO;
import shu.java.csky.vo.page.MyFilePageVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Silence
 * @date: 2022/2/24 18:51
 * @description:
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    MyFileMapper myFileMapper;

    @Override
    public ResultVO getFilePageBySubjectCode(String subject, IPage<MyFile> page) {
        QueryWrapper<MyFile> wrapper = new QueryWrapper<>();
        wrapper.like("subject", subject)
                .orderByDesc("year")
                .orderByAsc("subject_code");
        myFileMapper.selectPage(page, wrapper);
        List<MyFile> records = page.getRecords();
        MyFilePageVo myFilePageVo = new MyFilePageVo();
        BeanUtils.copyProperties(page, myFilePageVo);
        myFilePageVo.setFileList(records);
        return new ResultVO(ResStatus.OK, "试题查询成功", myFilePageVo);
    }
}
