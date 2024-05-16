package com.shu.cloudordering.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shu.cloudordering.constants.Result;
import com.shu.cloudordering.mapper.CategoryMapper;
import com.shu.cloudordering.mapper.ProductMapper;
import com.shu.cloudordering.mapper.UsersMapper;
import com.shu.cloudordering.mapper.VendorMapper;
import com.shu.cloudordering.model.dto.*;
import com.shu.cloudordering.model.entity.*;
import com.shu.cloudordering.model.vo.last.CategoryInfoVo;
import com.shu.cloudordering.model.vo.last.CommentVo;
import com.shu.cloudordering.model.vo.last.VendorInfoVo;
import com.shu.cloudordering.model.vo.last.VendorListBaseVo;
import com.shu.cloudordering.model.vo.progress.ProductVendorVo;
import com.shu.cloudordering.service.ProductCommentsService;
import com.shu.cloudordering.service.VendorService;
import com.shu.cloudordering.utils.Jieba;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Silence
 * @description 针对表【vendor】的数据库操作Service实现
 * @createDate 2022-04-02 18:40:15
 */
@Service
public class VendorServiceImpl extends ServiceImpl<VendorMapper, Vendor> implements VendorService {

    @Resource
    private VendorMapper vendorMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductCommentsService productCommentsService;
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Result vendorList(VendorListDTO params) {
        Page<Vendor> page = params.getPage();

        // 取得查询字符串
        String text = params.getText();
        // 取得餐厅字符串
        Integer canteenId = params.getCanteenId();
        // 取得排序标准
        SortedBy sortedBy = params.getSortedBy();

        // Jieba分词
        Jieba jieba = new Jieba();
        String s = null;
        List<String> strings = null;
        if (text != null) {
            strings = jieba.segmenter.sentenceProcess(text);
            s = strings.get(0);
        }

//        if (strings != null) {
//
//            VendorListBaseVo vendorListVo = new VendorListBaseVo();
//            List<Vendor> vendorArrayList = new ArrayList<>();
//            for (String string : strings) {
//                // 定义中间对象
//                Page<Vendor> vendorPage;
//                // 定义查询条件
//                QueryWrapper<Vendor> vendorQueryWrapper = new QueryWrapper<>();
//                // 根据传入的查询条件,进行处理
//                handleVendorQueryWrapper(vendorQueryWrapper, string, canteenId, sortedBy);
//
//                // 在vendor表中查询
//                vendorPage = page(page, vendorQueryWrapper);
//
//                List<Vendor> records = vendorPage.getRecords();
//                vendorPage.get
//
//                vendorArrayList.addAll(records);
//            }
//            vendorListVo.setVendorList(vendorArrayList);
//            BeanUtils.copyProperties(vendorPage, vendorListVo);
//            return Result.success("Vendor分页查询成功", vendorListVo);
//
//        }


        // 定义中间对象
        Page<Vendor> vendorPage;
        // 定义查询条件
        QueryWrapper<Vendor> vendorQueryWrapper = new QueryWrapper<>();
        // 根据传入的查询条件,进行处理
        handleVendorQueryWrapper(vendorQueryWrapper, s, canteenId, sortedBy);

        // 在vendor表中查询
        vendorPage = page(page, vendorQueryWrapper);

        VendorListBaseVo vendorListVo = new VendorListBaseVo();
        List<Vendor> vendorArrayList = new ArrayList<>(vendorPage.getRecords());
        vendorListVo.setVendorList(vendorArrayList);
        BeanUtils.copyProperties(vendorPage, vendorListVo);
        return Result.success("Vendor分页查询成功", vendorListVo);
    }

    @Override
    public Result getProductInfoByVendorId(Integer vendorId) {
        // 初始化返回的对象
        VendorInfoVo vendorInfoVo = new VendorInfoVo();
        // 根据vendorId查询到Vendor对象，获取它的名称、图片等信息
        Vendor vendor = vendorMapper.selectById(vendorId);
        BeanUtils.copyProperties(vendor, vendorInfoVo);
        // 根据vendorId, 在category表中查询到每一条分类
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("vendor_id", vendorId);
        List<Category> categoryList = categoryMapper.selectList(categoryQueryWrapper);

        // 设置Category列表
        List<CategoryInfoVo> categoryInfoVoList = new ArrayList<>();
        // 遍历得到的category列表
        // 根据每一个categoryId获取每一种类下的product,查询product表
        for (Category category : categoryList) {
            CategoryInfoVo categoryInfoVo = new CategoryInfoVo();
            // 设置该分类的分类名称
            categoryInfoVo.setCategoryName(category.getCategoryName());
            // 获取该分类下的product列表
            List<ProductVendorVo> productVendorVoList = new ArrayList<>();
            Integer categoryId = category.getCategoryId();
            QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
            productQueryWrapper.eq("category_id", categoryId);
            List<Product> productList = productMapper.selectList(productQueryWrapper);
            // 遍历每一个product
            for (Product product : productList) {
                ProductVendorVo productVendorVo = new ProductVendorVo();
                BeanUtils.copyProperties(product, productVendorVo);
                productVendorVoList.add(productVendorVo);
            }
            // 设置ProductVendorVo
            categoryInfoVo.setProductVendorVoList(productVendorVoList);
            categoryInfoVoList.add(categoryInfoVo);
        }
        vendorInfoVo.setCategoryInfoVoList(categoryInfoVoList);
        return Result.success("商户信息查询成功", vendorInfoVo);
    }

    @Override
    public Result vendorFuzzyQuery(QueryPageParam params) {
        Page<Vendor> page = params.getPage();
        String text = params.getText();
        return null;

    }

    @Override
    public void handleVendorQueryWrapper(QueryWrapper<Vendor> vendorQueryWrapper, String text, Integer canteenId, SortedBy sortedBy) {
        // 设置正在营业的排在前面
        vendorQueryWrapper.orderByAsc("in_business");

        // 传入了canteenId
        if (canteenId != null) vendorQueryWrapper.eq("canteen_id", canteenId);
        // 使用了模糊查询
        if (text != null) vendorQueryWrapper.like("vendor_name", text);
        // 使用了排序功能
        if (sortedBy != null) {
            // 取出销量、评分、价格三个指标
            Integer byTotalSold = sortedBy.getByTotalSold();
            Integer byScore = sortedBy.getByScore();
            Integer byPrice = sortedBy.getByPrice();

            // 传入了销量
            if (byTotalSold != null && byTotalSold == 2) vendorQueryWrapper.orderByAsc("total_sold");
            else vendorQueryWrapper.orderByDesc("total_sold");

            // 传入了评分
            if (byScore != null && byScore == 2) vendorQueryWrapper.orderByAsc("score");
            else vendorQueryWrapper.orderByDesc("score");

            // 传入了价格
            if (byPrice != null && byPrice == 2) vendorQueryWrapper.orderByAsc("average_price");
            else vendorQueryWrapper.orderByDesc("average_price");
        }
    }

    @Override
    public Result commentList(CommentPageDTO params) {
        String vendorId = params.getVendorId();
        QueryWrapper<ProductComments> productCommentsQueryWrapper = new QueryWrapper<>();
        productCommentsQueryWrapper.eq("vendor_id", vendorId);

        Page<ProductComments> page = productCommentsService.page(params.getPage(), productCommentsQueryWrapper);
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();

        ArrayList<CommentVo> commentVos = new ArrayList<>();
        for (ProductComments record : page.getRecords()) {
            CommentVo commentVo = new CommentVo();
            commentVo.setComment(record.getCommContent());
            commentVo.setCommentTime(record.getCommTime());
            commentVo.setScore(record.getScore());
//            获取图片列表
            String commImgs = record.getCommImgs();
            if (commImgs != null && !commImgs.equals("")) {
                List<String> strList = Arrays.asList(commImgs.split(" "));
                commentVo.setImgList(strList);
            }


            String userId = record.getUserId();
            Users users = usersMapper.selectById(userId);
            commentVo.setAvatar(users.getUserImg());
            commentVo.setUserName(users.getUsername());


            commentVos.add(commentVo);
        }


        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("pages", pages);
        stringObjectHashMap.put("size", size);
        stringObjectHashMap.put("total", total);

        stringObjectHashMap.put("commentList", commentVos);


        return Result.success("评论列表查询成功", stringObjectHashMap);
    }

    @Override
    public Result updateVendorInfo(UpdateVendorInfoDTO params) {
        Integer vendorId = params.getVendorId();
        String vendorImg = params.getVendorImg();
        String vendorName = params.getVendorName();
        String announcement = params.getAnnouncement();
        Integer inBusiness = params.getInBusiness();

        if (vendorId == null) {
            return Result.fail("请传入vendorId");
        }

        Vendor vendor = vendorMapper.selectById(vendorId);
        if (StrUtil.isNotBlank(vendorName)) {
            vendor.setVendorName(vendorName);
        }
        if (StrUtil.isNotBlank(vendorImg)) {
            vendor.setVendorImg(vendorImg);
        }
        if (StrUtil.isNotBlank(announcement)) {
            vendor.setAnnouncement(announcement);
        }
        if (inBusiness != null) {
            vendor.setInBusiness(inBusiness);
        }

        vendorMapper.updateById(vendor);
        return Result.success("商家信息更新成功", null);

    }

    @Override
    public Result addProduct(CategoryInfoVo params) {
        String categoryName = params.getCategoryName();
        Integer vendorId = params.getVendorId();
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName).eq("vendor_id", vendorId);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        Integer key;
        // 如果该分类没有被创建过
        if (category == null) {
            Category insertCategory = new Category();
            BeanUtils.copyProperties(params, insertCategory);
            // 获得主键
            categoryMapper.insert(insertCategory);
            key = insertCategory.getCategoryId();
        } else {
            key = category.getCategoryId();
        }

        // 获取添加的商品列表
        List<ProductVendorVo> productVendorVoList = params.getProductVendorVoList();

        for (ProductVendorVo productVendorVo : productVendorVoList) {
            // 设置属性
            Product product = new Product();
            product.setProductName(productVendorVo.getProductName());
            product.setProductImg(productVendorVo.getProductImg());
            product.setSellPrice(productVendorVo.getSellPrice());
            product.setStock(productVendorVo.getStock());

            // 设置分类Id
            product.setCategoryId(key);

            product.setCreateTime(new Date());
            product.setUpdateTime(new Date());
            // 插入数据库
            productMapper.insert(product);
        }

        return Result.success("商品信息添加成功", null);

    }

    @Override
    public Result updateProduct(ProductDTO params) {
        Integer productId = params.getProductId();
        String productName = params.getProductName();
        String productImg = params.getProductImg();
        Double sellPrice = params.getSellPrice();
        Integer stock = params.getStock();
        Integer productStatus = params.getProductStatus();

        Product product = new Product();
        product.setProductId(productId);
        if (StrUtil.isNotBlank(productName)) {
            product.setProductName(productName);
        }
        if (StrUtil.isNotBlank(productImg)) {
            product.setProductImg(productImg);
        }
        if (sellPrice != null) {
            product.setSellPrice(sellPrice);
        }
        if (stock != null) {
            product.setStock(stock);
        }
        if (productStatus != null) {
            product.setProductStatus(productStatus);
        }

        product.setUpdateTime(new Date());

        productMapper.updateById(product);

        return Result.success("商品更新成功", null);
    }

    @Override
    public Result categoryList(Integer vendorId) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("vendor_id", vendorId);
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
        List<String> strings = new ArrayList<>();
        for (Category category : categories) {
            String categoryName = category.getCategoryName();
            strings.add(categoryName);
        }
        return Result.success("商家分类列表名称查询成功", strings);
    }
}




