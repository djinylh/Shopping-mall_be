package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;
    private final OrderItemMapper orderItemMapper;
    private final CartMapper cartMapper;


    @Transactional
    public int orderItem(OrderPostDto dto) {
        return orderMapper.save(dto);
    }

    public int saveOrder(OrderPostReq req, int logginedId) {
//        상품 정보 DB로 부터 가져온다
        List<ItemGetRes> itemList = itemMapper.findAllByIdin(req.getItemIds()); //총 구매가격
        log.info("itemList={}", itemList);
        int amount = 0;
        for (ItemGetRes item : itemList) {
            amount += item.getPrice() - (item.getPrice() * item.getDiscountPer()) / 100;
        }
        log.info("amount={}", amount);
        // OrderPostDto 객체화 하시고 데이터에 넣어주세요
        OrderPostDto Orderpost = new OrderPostDto();
        Orderpost.setMemberId(logginedId);
        Orderpost.setName(req.getName());
        Orderpost.setAddress(req.getAddress());
        Orderpost.setPayment(req.getPayment());
        Orderpost.setCardNumber(req.getCardNumber());
        Orderpost.setAmount(amount);
        log.info("before = Orderpost={}", Orderpost);

        orderMapper.save(Orderpost);
        log.info("after-Orderpost={}", Orderpost);
        //
        //OrderItemPostDto 객체화
        OrderItemPostDto orderItempost =  new OrderItemPostDto(Orderpost.getOrderId(), req.getItemIds());

        orderItemMapper.save(orderItempost);
        cartMapper.deleteByMemberId(logginedId);


        return 1;
    }

    public List<OrderGetRes> findAllByMemberId(int memberId) {
        return  orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }
    public OrderDetailGetRes detail(OrderDetailGetReq req) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
        log.info("result={}", result);
        return result;
    }

}
