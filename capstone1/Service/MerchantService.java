package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    private ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getAll() {
        return merchants;
    }

    public void add(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean update(String id, Merchant updatedMerchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equalsIgnoreCase(id)) {
                merchants.set(i, updatedMerchant);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equalsIgnoreCase(id)) {
                merchants.remove(merchant);
                return true;
            }
        }
        return false;
    }

    public boolean isMerchantsExist(String merchantsid){
        for (Merchant m:merchants){
            if(m.getId().equals(merchantsid))
                return true;
        }
        return false;
    }


}
