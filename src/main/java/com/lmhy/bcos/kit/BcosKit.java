package com.lmhy.bcos.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lmhy.bcos.base.Constant;
import com.lmhy.bcos.contract.LUser;
import com.lmhy.bcos.contract.SUser;
import com.lmhy.bcos.ext.Bcos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class BcosKit {
    @Data
    @ToString
    @AllArgsConstructor
    public static class Accout {
        private String pubKey;
        private String prvKey;
        private String address;
    }

    public static Accout generateAccount() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            BigInteger pub = keyPair.getPrivateKey();
            BigInteger prv = keyPair.getPublicKey();

            String pubStr = Numeric.toHexStringNoPrefix(pub);
            String prvStr = Numeric.toHexStringNoPrefix(prv);

            String address = Keys.getAddress(keyPair);
            return new Accout(prvStr,pubStr,address);
        } catch (Exception e) {
            log.error("BcosKit@generateAccount error", e);
        }
        return null;
    }

    public static void main(String[] args) {
        Accout accout = generateAccount();
        log.info("{}",accout);
    }

    public static void deploySys() {
        try {
            SUser sUser = SUser.deploy(Bcos.web3j, Bcos.bcosRawTxManager, Constant.GAS_PRICE, Constant.GAS_LIMIT).send();
            String sUserAdr = sUser.getContractAddress();

            LUser lUser = LUser.deploy(Bcos.web3j, Bcos.bcosRawTxManager, Constant.GAS_PRICE, Constant.GAS_LIMIT).send();
            String lUserAdr = lUser.getContractAddress();

            log.info("生成的合约地址如下 s:{} l:{}", sUserAdr, lUserAdr);

            JSONObject json = new JSONObject(2);


            json.put("sUser", sUserAdr);
            json.put("lUser", lUserAdr);

            String jsonStr = json.toJSONString();

            log.info("json 字符串为:{}", jsonStr);
            Files.write(Paths.get("c:/bcos.json"), jsonStr.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static HashMap getAdr() {
        try {
            List<String> strList = Files.readAllLines(Paths.get("c:/bcos.json"));
            String jsonStr = StringUtils.join(strList, "");
            log.info(jsonStr);
            return JSON.parseObject(jsonStr, HashMap.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
