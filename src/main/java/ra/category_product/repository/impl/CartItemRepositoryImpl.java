package ra.category_product.repository.impl;

import ra.category_product.model.CartItem;
import ra.category_product.repository.ICartItemRepository;

import java.util.ArrayList;
import java.util.List;

public class CartItemRepositoryImpl implements ICartItemRepository {
    public static List<CartItem> cartItems = new ArrayList<>();

    @Override
    public List<CartItem> getAll() {
        return cartItems;
    }

    @Override
    public void save(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    @Override
    public void update(Integer id, CartItem cartItem) {
       for (int i = 0; i < cartItems.size(); i++) {
           if (cartItems.get(i).getId() == id){
               cartItems.set(i, cartItem);
               return;
           }
       }
    }

    @Override
    public CartItem findById(Integer id) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getId() == id){
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        CartItem indexDelete = findById(id);
        cartItems.remove(indexDelete);
    }

    @Override
    public List<CartItem> searchByName(String name) {
        return List.of();
    }
}
