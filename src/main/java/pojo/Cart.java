package pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 购物车模块
 * @ClassName Cart
 * @PackageNmae pojo
 * @Author Yanhao
 * @Date 2021/3/8 11:00
 * @Version 1.0
 */
public class Cart {
    private Integer totalCount;
    //购物车的金额总价
    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public Integer getTotalCount() {
        totalCount = 0;

        for(CartItem value : items.values()){
            totalCount += value.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        totalPrice  = BigDecimal.valueOf(0);

        for(CartItem value : items.values()){
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer,CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer,CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    //添加商品项
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());

        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else{
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    //添加商品项
    public void deleteItem(Integer id){
        items.remove(id);
    }

    //清空购物车
    public void clear(){
        items.clear();
    }

    //修改商品数量
    public void  updateCount(Integer id , Integer count){
        CartItem cart = items.get(id);
        if(cart != null){
            cart.setCount(count);
            cart.setTotalPrice(cart.getPrice().multiply(new BigDecimal(cart.getCount())));
        }
    }

}
