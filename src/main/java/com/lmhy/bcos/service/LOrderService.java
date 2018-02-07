package com.lmhy.bcos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lmhy.bcos.contract.LOrder;
import com.lmhy.bcos.ext.Bcos;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;

@Slf4j
@Service
public class LOrderService {
    private LOrder get(String adr) {
        return LOrder.load(adr, Bcos.web3j, Bcos.bcosRawTxManager, Bcos.GAS_PRICE, Bcos.GAS_LIMIT);
    }

    public JSONObject create(Integer fee, String goods, Integer weight, String from, String to, String go) {
        String myCode = "my" + System.currentTimeMillis();
        String wlCode = "wl" + System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject(3);
        try {
            LOrder lOrder = LOrder.deploy(Bcos.web3j, Bcos.bcosRawTxManager, Bcos.GAS_PRICE, Bcos.GAS_LIMIT, myCode, new BigInteger(fee + ""), goods, new BigInteger(weight + ""), from, to, go, wlCode).send();
            String address = lOrder.getContractAddress();
            log.info("address:{}", address);
            jsonObject.put("myCode", myCode);
            jsonObject.put("wlCode", wlCode);
            jsonObject.put("address", address);
            jsonObject.put("fee",fee);
            jsonObject.put("goods",goods);
            jsonObject.put("weight",weight);
            jsonObject.put("from",from);
            jsonObject.put("to",to);
            jsonObject.put("go",go);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String createYd(String adr) {
        LOrder lOrder = get(adr);
        String ydCode = "yd" + System.currentTimeMillis();
        try {
            TransactionReceipt tr = lOrder.geneYd(ydCode).send();
            if (tr != null)
                return ydCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public String createTs(String adr, String carNo, Integer weight) {
        LOrder lOrder = get(adr);
        try {
            String tsCode = "ts" + System.currentTimeMillis();
            TransactionReceipt tr = lOrder.geneTs(tsCode, carNo, new BigInteger(weight + "")).send();
            if (tr != null)
                return tsCode;
        } catch (Exception e) {
            log.error("", e);
        }
        return StringUtils.EMPTY;
    }

    public JSONArray findTs(String adr) {
        LOrder lOrder = get(adr);
        JSONArray array = new JSONArray();
        try {
            Integer size = lOrder.ydSize().send().intValue();
            if (size == 0) return array;
            for (int i = 0; i < size; i++) {
                Tuple4<String, String, BigInteger, BigInteger> tuple4 = lOrder.ydMap(new BigInteger(i + "")).send();
                JSONObject jsonObject = new JSONObject(6);
                jsonObject.put("code", tuple4.getValue1());
                jsonObject.put("carNo", tuple4.getValue2());
                jsonObject.put("weight", tuple4.getValue3());
                jsonObject.put("state", tuple4.getValue4());
                array.add(jsonObject);
            }
        } catch (Exception e) {
            log.error("findTs", e);
        }
        return array;
    }

    public String createReceipt(String adr, String reNo) {
        LOrder lOrder = get(adr);
        try {
            String reCode = "rc" + System.currentTimeMillis();
            TransactionReceipt tr = lOrder.toReceipt(reNo, reCode).send();
            if (tr != null)
                return reCode;
        } catch (Exception e) {
            log.error("createReceipt", e);
        }
        return StringUtils.EMPTY;
    }

    public JSONArray findReceipt(String adr) {
        LOrder lOrder = get(adr);
        JSONArray array = new JSONArray();
        try {
            Integer size = lOrder.rcSize().send().intValue();
            if (size == 0) return array;
            for (int i = 0; i < size; i++) {
                Tuple3<String, String, BigInteger> tuple3 = lOrder.rcMap(new BigInteger(i + "")).send();
                JSONObject jsonObject = new JSONObject(3);
                jsonObject.put("code", tuple3.getValue1());
                jsonObject.put("reNo", tuple3.getValue2());
                jsonObject.put("time", tuple3.getValue3());
                array.add(jsonObject);
            }
        } catch (Exception e) {
            log.error("findReceipt", e);
        }
        return array;
    }

    public JSONArray findInfo(String adr) {
        JSONArray resultArr = new JSONArray();
        JSONArray tsArr = findTs(adr);
        JSONArray rcArr = findReceipt(adr);
        for (int i = 0; i < tsArr.size(); i++) {
            JSONObject ele = tsArr.getJSONObject(i);
            for (int j = 0; j < rcArr.size(); j++) {
                JSONObject rEle = rcArr.getJSONObject(j);
                if (StringUtils.equals(ele.getString("code"), rEle.getString("reNo"))) {
                    ele.put("rcCode", rEle.getString("code"));
                    ele.put("rcTime", rEle.get("time"));
                    break;
                }
            }
            resultArr.add(ele);
        }
        return resultArr;

    }
}
