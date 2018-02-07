package com.lmhy.bcos.ctrl;

import com.lmhy.bcos.base.ApiResult;
import com.lmhy.bcos.kit.BcosKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
public class IndexCtrl extends BaseCtrl {


    @GetMapping("init")
    public ApiResult init() {
//        bcosService.deploySys();
        HashMap map = BcosKit.getAdr();
        log.info("部署用户合约完成");
        return toSuccess(map);
    }

    @GetMapping("")
    public String index() throws IOException {
        String url = "http://172.16.1.190:8545";
        Web3j web3 = Web3j.build(new HttpService(url));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        BigInteger gasPrice = new BigInteger("100000000"), gasLimit = gasPrice;
        Credentials credentials = null;

        try {
            credentials = WalletUtils.loadCredentials("1111", new File("D:\\ideaspace\\lmhy-bcos\\src\\main\\resources\\wallet.json"));

            System.out.println("address: " + credentials.getAddress());
//            Demo demo = Demo.deploy(web3, new BcosRawTxManager(web3, credentials, 100, 100), gasPrice, gasLimit, "zfc").send();
//            System.out.println(demo.getContractAddress());
//            String s = demo.c().send();
//            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientVersion;
    }

    @GetMapping("upload")
    public ModelAndView uploadGet(ModelAndView modelMap){
        modelMap.setViewName("upload");

        return modelMap;
    }

    @PostMapping("upload")
    @ResponseBody
    public ApiResult upload(MultipartHttpServletRequest request){
        List<MultipartFile> files = request.getFiles("file");
        String param = request.getParameter("param");
        log.info(param);
        for (MultipartFile file : files) {
            log.info(file.getOriginalFilename());
        }
        log.info(param);

        return toSuccess();
    }
}
