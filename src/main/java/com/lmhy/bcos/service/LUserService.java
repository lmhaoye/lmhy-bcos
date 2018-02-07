package com.lmhy.bcos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lmhy.bcos.contract.CKit;
import com.lmhy.bcos.contract.LUser;
import com.lmhy.bcos.ext.Bcos;
import com.lmhy.bcos.kit.BcosKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;

@Service
@Slf4j
public class LUserService {
    private LUser lUser;

    public LUserService() {
        String adr = CKit.get("lUser");
        lUser = LUser.load(adr, Bcos.web3j, Bcos.bcosRawTxManager, Bcos.GAS_PRICE, Bcos.GAS_LIMIT);
    }

    public String reg(String name, Integer type) {
        BcosKit.Accout accout = BcosKit.generateAccount();
        log.info("生成用户数据为：{}", accout);
        try {
            TransactionReceipt tr = lUser.add(accout.getAddress(), name, new BigInteger(type + "")).send();
            if (tr != null)
                return accout.getAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public JSONArray findAll() {
        JSONArray array = new JSONArray();
        try {
            Integer size = lUser.umapSize().send().intValue();
            for (int i = 0; i < size; i++) {
                Tuple3<String, String, BigInteger> tuple3 = lUser.umap(new BigInteger(i + "")).send();
                JSONObject jsonObject = new JSONObject(3);
                jsonObject.put("adr", tuple3.getValue1());
                jsonObject.put("name", tuple3.getValue2());
                jsonObject.put("type", tuple3.getValue3());
                array.add(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public void saveOrder(String adr) {
        try {
            lUser.addOrder(adr).send();
        } catch (Exception e) {
            log.error("saveOrder", e);
        }
    }

}
