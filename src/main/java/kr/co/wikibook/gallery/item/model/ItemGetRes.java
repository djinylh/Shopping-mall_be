package kr.co.wikibook.gallery.item.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemGetRes {
    private int id;
    private String name;
    private String imgPath;
    private int price;
    private int discountPer;

}
