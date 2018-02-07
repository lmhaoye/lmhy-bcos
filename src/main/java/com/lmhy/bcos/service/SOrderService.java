package com.lmhy.bcos.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lmhy.bcos.contract.SOrder;
import com.lmhy.bcos.ext.Bcos;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;

import java.math.BigInteger;

@Service
@Slf4j
public class SOrderService {
    @Autowired
    private SUserService sUserService;

    private SOrder get(String address) {
        return SOrder.load(address, Bcos.web3j, Bcos.bcosRawTxManager, Bcos.GAS_PRICE, Bcos.GAS_LIMIT);
    }

    public JSONObject create(Integer fee, String from, String to) {
        String code = "tx-" + System.currentTimeMillis();
        try {
            SOrder sOrder = SOrder.deploy(Bcos.web3j, Bcos.bcosRawTxManager, Bcos.GAS_PRICE, Bcos.GAS_LIMIT, code, new BigInteger(fee + ""), from, to).send();
            String address = sOrder.getContractAddress();
            sUserService.saveOrder(address);
            log.info("生成订单[{}]成功", code);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address",address);
            jsonObject.put("code",code);

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String splic(String adr, String from, String to, Integer fee) {
        SOrder sOrder = get(adr);
        try {
            String no = "txcd" + System.currentTimeMillis();
            TransactionReceipt tr = sOrder.splitOrder(no, from, to, new BigInteger(fee + "")).send();
            if (tr != null)
                return no;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public String load(String adr, String reNo, String from, String to, Integer fee) {
        SOrder sOrder = get(adr);
        try {
            String no = "txld" + System.currentTimeMillis();
            TransactionReceipt tr = sOrder.loadFun(reNo, no, from, to, new BigInteger(fee + "")).send();
            if (tr != null) return no;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public String repay(String adr, String reNo, String from, String to, Integer fee) {
        SOrder sOrder = get(adr);
        try {
            String no = "txrp" + System.currentTimeMillis();
            TransactionReceipt tr = sOrder.repayFun(reNo, no, from, to, new BigInteger(fee + "")).send();
            if (tr != null) return no;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public JSONArray findByArr(String[] arr) {
        JSONArray array = new JSONArray(arr.length);
        SOrder sOrder;
        for (String adr : arr) {
            sOrder = get(adr);
            try {
                Tuple2<String, BigInteger> info = sOrder.info().send();
                String from = sOrder.from().send();
                String to = sOrder.to().send();
                BigInteger time = sOrder.createTime().send();
                BigInteger cmapSize = sOrder.cmapSize().send();
                BigInteger lmapSize = sOrder.lmapSize().send();
                BigInteger rmapSize = sOrder.rmapSize().send();

                JSONObject jsonObject = new JSONObject(9);
                jsonObject.put("adr", adr);
                jsonObject.put("code", info.getValue1());
                jsonObject.put("fee", info.getValue2());
                jsonObject.put("from", from);
                jsonObject.put("to", to);
                jsonObject.put("createTime", time);
                jsonObject.put("cmapSize", cmapSize);
                jsonObject.put("lmapSize", lmapSize);
                jsonObject.put("rmapSize", rmapSize);

                array.add(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    public JSONArray findData(String adr, String queryType) {
        SOrder sOrder = get(adr);
        JSONArray array = new JSONArray();
        try {
            switch (queryType) {
                case "splic": {
                    BigInteger cmapSize = sOrder.cmapSize().send();
                    Integer size = cmapSize.intValue();
                    if (size == 0) return array;
                    for (int i = 0; i < size; i++) {
                        Tuple5<String, String, String, BigInteger, BigInteger> tuple5 = sOrder.cmap(new BigInteger(i + "")).send();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", i);
                        jsonObject.put("no", tuple5.getValue1());
                        jsonObject.put("from", tuple5.getValue2());
                        jsonObject.put("ow", tuple5.getValue3());
                        jsonObject.put("time", tuple5.getValue4());
                        jsonObject.put("fee", tuple5.getValue5());
                        array.add(jsonObject);
                    }
                }
                break;
                case "load": {
                    BigInteger lmapSize = sOrder.lmapSize().send();
                    Integer size = lmapSize.intValue();
                    if (size == 0) return array;
                    for (int i = 0; i < size; i++) {
                        Tuple6<String, String, String, String, BigInteger, BigInteger> tuple6 = sOrder.lmap(new BigInteger(i + "")).send();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("no", tuple6.getValue1());
                        jsonObject.put("reNo", tuple6.getValue2());
                        jsonObject.put("from", tuple6.getValue3());
                        jsonObject.put("to", tuple6.getValue4());
                        jsonObject.put("fee", tuple6.getValue5());
                        jsonObject.put("time", tuple6.getValue6());

                        array.add(jsonObject);
                    }
                }
                break;
                case "repay": {
                    BigInteger rmapSize = sOrder.rmapSize().send();
                    Integer size = rmapSize.intValue();
                    if (size == 0) return array;
                    for (int i = 0; i < size; i++) {
                        Tuple6<String, String, String, String, BigInteger, BigInteger> tuple6 = sOrder.rmap(new BigInteger(i + "")).send();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("no", tuple6.getValue1());
                        jsonObject.put("reNo", tuple6.getValue2());
                        jsonObject.put("from", tuple6.getValue3());
                        jsonObject.put("to", tuple6.getValue4());
                        jsonObject.put("fee", tuple6.getValue5());
                        jsonObject.put("time", tuple6.getValue6());
                        array.add(jsonObject);
                    }
                }
                break;
            }
        } catch (Exception e) {
            log.error("findOne", e);
        }
        return array;
    }

    public JSONObject findOne(String adr) {
        JSONObject jsonObject = new JSONObject(12);
        try {
            SOrder sOrder = get(adr);
            Tuple2<String, BigInteger> info = sOrder.info().send();
            String from = sOrder.from().send();
            String to = sOrder.to().send();
            BigInteger time = sOrder.createTime().send();
            BigInteger cmapSize = sOrder.cmapSize().send();
            BigInteger lmapSize = sOrder.lmapSize().send();
            BigInteger rmapSize = sOrder.rmapSize().send();


            jsonObject.put("adr", adr);
            jsonObject.put("code", info.getValue1());
            jsonObject.put("fee", info.getValue2());
            jsonObject.put("from", from);
            jsonObject.put("to", to);
            jsonObject.put("createTime", time);
            jsonObject.put("cmapSize", cmapSize);
            jsonObject.put("lmapSize", lmapSize);
            jsonObject.put("rmapSize", rmapSize);

            JSONArray cArr = findData(adr, "splic");
            JSONArray lArr = findData(adr, "load");
            JSONArray rArr = findData(adr, "repay");

            jsonObject.put("cmapArr", cArr);
            jsonObject.put("lmapArr", lArr);
            jsonObject.put("rmapArr", rArr);
        } catch (Exception e) {
            log.error("findOne", e);
        }
        return jsonObject;
    }
}
