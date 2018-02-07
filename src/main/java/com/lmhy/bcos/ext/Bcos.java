package com.lmhy.bcos.ext;

import com.lmhy.bcos.contract.CKit;
import com.lmhy.bcos.kit.BcosKit;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class Bcos {

    public static Web3j web3j = null;
    public static BcosRawTxManager bcosRawTxManager = null;
    public static final BigInteger GAS_PRICE = new BigInteger("100000000");
    public static final BigInteger GAS_LIMIT = new BigInteger("100000000");

    public Bcos(BcosConfig config) {
        this.web3j = Web3j.build(new HttpService(config.getUrl()));
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(config.getPwd(), new File(config.getWallet()));
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }
        this.bcosRawTxManager = new BcosRawTxManager(this.web3j, credentials, 100, 100);
//        BcosKit.deploySys();
        HashMap map = BcosKit.getAdr();
        if(map!=null){
            CKit.init(map);
        }
    }
}
