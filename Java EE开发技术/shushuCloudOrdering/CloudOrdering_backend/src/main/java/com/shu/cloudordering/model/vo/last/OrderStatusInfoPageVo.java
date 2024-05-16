package com.shu.cloudordering.model.vo.last;

import com.shu.cloudordering.model.vo.basevo.PageBaseVo;
import com.shu.cloudordering.model.vo.progress.OrderBriefVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Silence
 * @date: 2022/5/7 18:48
 * @description: 根据用户ID查询所有订单时返回的Vo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusInfoPageVo extends PageBaseVo {
    private List<OrderBriefVo> orderBriefVoList;
}
