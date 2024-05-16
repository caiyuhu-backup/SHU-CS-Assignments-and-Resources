package com.shu.cloudordering.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.model.entity.ProductComments;
import com.shu.cloudordering.service.ProductCommentsService;
import com.shu.cloudordering.mapper.ProductCommentsMapper;
import org.springframework.stereotype.Service;

/**
* @author Silence
* @description 针对表【product_comments(商品评价 )】的数据库操作Service实现
* @createDate 2022-04-02 18:40:15
*/
@Service
public class ProductCommentsServiceImpl extends ServiceImpl<ProductCommentsMapper, ProductComments>
    implements ProductCommentsService{

}




