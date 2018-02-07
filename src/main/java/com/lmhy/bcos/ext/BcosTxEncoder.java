package com.lmhy.bcos.ext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

public class BcosTxEncoder
{
    private BigInteger blockLimited = new BigInteger("100");
    private BigInteger random;

    public List<RlpType> asRlpValues(RawTransaction rawTransaction, Sign.SignatureData signatureData)
    {
        List<RlpType> result = new ArrayList();

        result.add(RlpString.create(this.random));

        result.add(RlpString.create(rawTransaction.getGasPrice()));

        result.add(RlpString.create(rawTransaction.getGasLimit()));

        result.add(RlpString.create(this.blockLimited));

        String to = rawTransaction.getTo();
        if ((to != null) && (to.length() > 0)) {
            result.add(RlpString.create(Numeric.toBigInt(to)));
        } else {
            result.add(RlpString.create(""));
        }
        result.add(RlpString.create(rawTransaction.getValue()));

        byte[] data = Numeric.hexStringToByteArray(rawTransaction.getData());
        result.add(RlpString.create(data));
        if (signatureData != null)
        {
            result.add(RlpString.create(signatureData.getV()));
            result.add(RlpString.create(signatureData.getR()));
            result.add(RlpString.create(signatureData.getS()));
        }
        return result;
    }

    public BigInteger getBlockLimited()
    {
        return this.blockLimited;
    }

    public void setBlockLimited(BigInteger blockLimited)
    {
        this.blockLimited = blockLimited;
    }

    public byte[] signMessage(RawTransaction rawTransaction, Credentials credentials)
    {
        byte[] encodedTransaction = encode(rawTransaction);
        Sign.SignatureData signatureData = Sign.signMessage(
                encodedTransaction, credentials.getEcKeyPair());

        return encode(rawTransaction, signatureData);
    }

    public byte[] signMessage(RawTransaction rawTransaction, byte chainId, Credentials credentials)
    {
        byte[] encodedTransaction = encode(rawTransaction, chainId);
        Sign.SignatureData signatureData = Sign.signMessage(
                encodedTransaction, credentials.getEcKeyPair());

        Sign.SignatureData eip155SignatureData = createEip155SignatureData(signatureData, chainId);
        return encode(rawTransaction, eip155SignatureData);
    }

    public Sign.SignatureData createEip155SignatureData(Sign.SignatureData signatureData, byte chainId)
    {
        byte v = (byte)(signatureData.getV() + (chainId << 1) + 8);

        return new Sign.SignatureData(
                v, signatureData.getR(), signatureData.getS());
    }

    public byte[] encode(RawTransaction rawTransaction)
    {
        return encode(rawTransaction, null);
    }

    public byte[] encode(RawTransaction rawTransaction, byte chainId)
    {
        Sign.SignatureData signatureData = new Sign.SignatureData(
                chainId, new byte[0], new byte[0]);
        return encode(rawTransaction, signatureData);
    }

    private byte[] encode(RawTransaction rawTransaction, Sign.SignatureData signatureData)
    {
        List<RlpType> values = asRlpValues(rawTransaction, signatureData);
        RlpList rlpList = new RlpList(values);
        return RlpEncoder.encode(rlpList);
    }

    public void setRandomId(BigInteger ramdomid)
    {
        this.random = ramdomid;
    }
}
