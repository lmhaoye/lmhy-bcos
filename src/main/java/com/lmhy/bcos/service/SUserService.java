package com.lmhy.bcos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lmhy.bcos.base.Constant;
import com.lmhy.bcos.contract.CKit;
import com.lmhy.bcos.contract.SUser;
import com.lmhy.bcos.ext.Bcos;
import com.lmhy.bcos.kit.BcosKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;

@Service
@Slf4j
public class SUserService {
    private SUser sUser;

    public SUserService() {
        String address = CKit.get("sUser");
        sUser = SUser.load(address, Bcos.web3j, Bcos.bcosRawTxManager, Constant.GAS_PRICE, Constant.GAS_LIMIT);
    }

    private JSONObject mergeResult(Tuple5<BigInteger, String, BigInteger, String, BigInteger> tuple5) {
        JSONObject temp = new JSONObject(5);
        temp.put("id", tuple5.getValue1());
        temp.put("address", tuple5.getValue2());
        temp.put("cate", tuple5.getValue3());
        temp.put("name", tuple5.getValue4());
        temp.put("time", tuple5.getValue5());
        return temp;
    }


    public Integer reg(String name, Integer type) {
        BcosKit.Accout accout = BcosKit.generateAccount();
        if (accout == null)
            throw new RuntimeException("生成账户异常");
        log.info("生成的账户为:{}", accout);
        try {
            Integer id = sUser.len().send().intValue();
            TransactionReceipt tr = sUser.reg(name, accout.getAddress(), new BigInteger(type + "")).send();
            log.info("交易输出为:{}", tr);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject findByIndex(Integer _index) {
        try {
            Tuple5<BigInteger, String, BigInteger, String, BigInteger> tuple = sUser.accmap(new BigInteger(_index + "")).send();
            return mergeResult(tuple);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray findAll() {
        try {
            BigInteger len = sUser.len().send();
            int lenInt = len.intValue();
            JSONArray array = new JSONArray(lenInt);
            for (int i = 0; i < lenInt; i++) {
                Tuple5<BigInteger, String, BigInteger, String, BigInteger> tuple5 = sUser.accmap(new BigInteger(i + "")).send();
                array.add(mergeResult(tuple5));
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveOrder(String orderAdr) {
        try {
            TransactionReceipt tr = sUser.saveOrder(orderAdr).send();
            log.info("{}", tr);
        } catch (Exception e) {
            log.error("SUserService@saveOrder error", e);
        }
    }

    public String[] findAllOrder() {

        try {
            BigInteger lengthBig = sUser.orderSize().send();
            int size = lengthBig.intValue();
            String[] arr = new String[size];
            for (int i = 0; i < size; i++) {
                String adr = sUser.orderList(new BigInteger(i + "")).send();
                arr[i] = adr;
            }
            return arr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
