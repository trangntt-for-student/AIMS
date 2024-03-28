package isd.aims.main.entity.cart;

import isd.aims.main.common.exception.MediaNotAvailableException;
import isd.aims.main.entity.media.Media;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private List<CartMedia> lstCartMedia;
    private static Cart cartInstance;

    public static Cart getCart(){
        if(cartInstance == null) cartInstance = new Cart();
        return cartInstance;
    }

    private Cart(){
        lstCartMedia = new ArrayList<>();
    }

    public void addCartMedia(CartMedia cm){
        lstCartMedia.add(cm);
    }

    public void removeCartMedia(CartMedia cm){
        lstCartMedia.remove(cm);
    }

    public List getListMedia(){
        return lstCartMedia;
    }

    public void emptyCart(){
        lstCartMedia.clear();
    }

    public int getTotalMedia(){
        int total = 0;
        for (CartMedia obj : lstCartMedia) {
            total += obj.getQuantity();
        }
        return total;
    }

    public int calSubtotal(){
        int total = 0;
        for (CartMedia obj : lstCartMedia) {
            total += obj.getPrice()* obj.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException{
        boolean allAvai = true;
        for (CartMedia object : lstCartMedia) {
            int requiredQuantity = object.getQuantity();
            int availQuantity = object.getMedia().getQuantity();
            if (requiredQuantity > availQuantity) allAvai = false;
        }
        if (!allAvai) throw new MediaNotAvailableException("Some media not available");
    }

    public CartMedia checkMediaInCart(Media media){
        for (CartMedia cartMedia : lstCartMedia) {
            if (cartMedia.getMedia().getId() == media.getId()) return cartMedia;
        }
        return null;
    }

}
