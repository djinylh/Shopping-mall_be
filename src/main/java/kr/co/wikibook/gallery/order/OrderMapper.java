package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.order.model.OrderPostDto;
import kr.co.wikibook.gallery.order.model.OrderPostReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    int save(OrderPostDto dto);
}
