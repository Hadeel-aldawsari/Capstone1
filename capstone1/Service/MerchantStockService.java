package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
private final MerchantService merchantService;
private final ProductService productService;
    public ArrayList<MerchantStock> getAll() {
        return merchantStocks;
    }

    public boolean add(MerchantStock merchantStock) {
        if(merchantService.isMerchantsExist(merchantStock.getMerchantid()) && productService.getProductByID(merchantStock.getProductid())!=null ) {
            merchantStocks.add(merchantStock);
            return true;
        }
        return false;
    }

    public boolean update(String id, MerchantStock updatedMerchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equalsIgnoreCase(id)) {
                merchantStocks.set(i, updatedMerchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getId().equalsIgnoreCase(id)) {
                merchantStocks.remove(merchantStock);
                return true;
            }
        }
        return false;
    }

//*****************
    //end point (11) add stock
    public boolean addStock(String productId, String merchantId, int additionalStock){
        for (MerchantStock m:merchantStocks){
            if(m.getMerchantid().equalsIgnoreCase(merchantId) && m.getProductid().equalsIgnoreCase(productId)){
                m.setStock(m.getStock()+additionalStock);
                return true;
            }
        }
return false;
    }
    //*********

    //method to check stock
    public boolean isInStock(String productid, String merchantId ){
for (MerchantStock m:merchantStocks){
    if(m.getProductid().equalsIgnoreCase(productid) && m.getMerchantid().equalsIgnoreCase(merchantId)){
       if( m.getStock()>0)return true;
    }}
return false;
    }


    //to get stock
    public int getStock( String merchantId ,String productid) {
        for (MerchantStock m : merchantStocks) {
            if (m.getProductid().equalsIgnoreCase(productid) && m.getMerchantid().equalsIgnoreCase(merchantId)) {
                return m.getStock();
            }
        }
        return 0;
    }

    // Reduce stock after a purchase
    public void reduceStock(String merchantId, OrderItem item) {
        for (MerchantStock m : merchantStocks) {
            if (m.getMerchantid().equals(merchantId) && m.getProductid().equals(item.getId())) {
                m.setStock(m.getStock() - (item.getQuantity()));
            }
        }
    }


    //restock
    public void restock(String merchantId, String productId,int quantity){
        for (MerchantStock m:merchantStocks){
            if (m.getMerchantid().equals(merchantId) && m.getProductid().equals(productId)){
                m.setStock(m.getStock()+quantity);
            }
        }
    }

    public boolean isMerchantsStockExist(String merchantsid){
        for (MerchantStock m:merchantStocks){
            if(m.getMerchantid().equals(merchantsid))

                return true;
        }
        return false;
    }






}
