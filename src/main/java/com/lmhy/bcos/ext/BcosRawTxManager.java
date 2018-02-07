package com.lmhy.bcos.ext;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Numeric;

public class BcosRawTxManager
        extends TransactionManager
{
    private final Web3j web3j;
    final Credentials credentials;
    private final byte chainId;
    private BcosTxEncoder transactionEncoder;
    private Random random;

    public BcosRawTxManager(Web3j web3j, Credentials credentials, byte chainId)
    {
        super(web3j,credentials.getAddress());
        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;

        this.random = new Random();
        this.random.setSeed(Calendar.getInstance().getTimeInMillis());

        this.transactionEncoder = new BcosTxEncoder();
    }

    public BcosRawTxManager(Web3j web3j, Credentials credentials, byte chainId, int attempts, int sleepDuration)
    {
        super(web3j, attempts, sleepDuration,credentials.getAddress());
        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;

        this.random = new Random();
        this.random.setSeed(Calendar.getInstance().getTimeInMillis());
        this.transactionEncoder = new BcosTxEncoder();
    }

    public BcosRawTxManager(Web3j web3j, Credentials credentials)
    {
        this(web3j, credentials, (byte)-1);
    }

    public BcosRawTxManager(Web3j web3j, Credentials credentials, int attempts, int sleepDuration)
    {
        this(web3j, credentials, (byte)-1, attempts, sleepDuration);
    }

    BigInteger getNonce()
            throws ExecutionException, InterruptedException
    {
        EthGetTransactionCount ethGetTransactionCount =
                (EthGetTransactionCount)this.web3j.ethGetTransactionCount(this.credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }

    public EthSendTransaction sendTransaction(BigInteger gasPrice, BigInteger gasLimit, String to, String data, BigInteger value)
    {
        BigInteger nonce = null;
        try {
            nonce = getNonce();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                to,
                value,
                data);

        try {
            return signAndSend(rawTransaction);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EthSendTransaction signAndSend(RawTransaction rawTransaction)
            throws ExecutionException, InterruptedException
    {
        Request<?, EthBlockNumber> blockNumber = this.web3j.ethBlockNumber();
        EthBlockNumber blockNumber2 = new EthBlockNumber();
        BigInteger blockLimited = null;
        try
        {
            blockNumber2 = (EthBlockNumber)blockNumber.send();
            blockLimited = blockNumber2.getBlockNumber();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        blockLimited = blockLimited.add(new BigInteger("100", 10));
        this.transactionEncoder.setBlockLimited(blockLimited);

        BigInteger ramdomid = new BigInteger(256, this.random);
        this.transactionEncoder.setRandomId(ramdomid);
        byte[] signedMessage;
        if (this.chainId > -1) {
            signedMessage = this.transactionEncoder.signMessage(rawTransaction, this.chainId, this.credentials);
        } else {
            signedMessage = this.transactionEncoder.signMessage(rawTransaction, this.credentials);
        }
        String hexValue = Numeric.toHexString(signedMessage);

        return (EthSendTransaction)this.web3j.ethSendRawTransaction(hexValue).sendAsync().get();
    }

    public String getFromAddress()
    {
        return this.credentials.getAddress();
    }
}
