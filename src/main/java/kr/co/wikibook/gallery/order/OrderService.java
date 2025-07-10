package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.OrderPostDto;
import kr.co.wikibook.gallery.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;


    public int orderItem(OrderPostDto dto) {
        return orderMapper.save(dto);
    }
    public int saveOrder(OrderPostReq req){
        //상품 정보 DB로 부터 가져온다
//        List<ItemGetRes> product = itemMapper.findAllByIdin();
        return 1;
    }


}
