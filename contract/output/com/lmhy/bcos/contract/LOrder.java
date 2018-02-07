package com.lmhy.bcos.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class LOrder extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b604051610b59380380610b59833981016040528080518201919060200180519190602001805182019190602001805191906020018051919060200180519190602001805191906020018051909101905060008880516100729291602001906100f5565b506001879055600286805161008b9291602001906100f5565b50600385905560048054600160a060020a03808716600160a060020a03199283161790925560058054868416908316179055600680549285169290911691909117905560078180516100e19291602001906100f5565b505060006009555061019095505050505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061013657805160ff1916838001178555610163565b82800160010185558215610163579182015b82811115610163578251825591602001919060010190610148565b5061016f929150610173565b5090565b61018d91905b8082111561016f5760008155600101610179565b90565b6109ba8061019f6000396000f30060606040526004361061008d5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663017e54f6811461009257806341be9855146100e557806346d9d7b5146101785780635c4b43a91461019d5780636f8b17ca146102be578063c19d93fb146102d1578063caf38397146102e4578063d1b8031e1461040d575b600080fd5b341561009d57600080fd5b6100e360046024813581810190830135806020601f820181900481020160405190810160405281815292919060208401838380828437509496506104a295505050505050565b005b34156100f057600080fd5b6100e360046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f0160208091040260200160405190810160405281815292919060208401838380828437509496506104be95505050505050565b341561018357600080fd5b61018b6107c8565b60405190815260200160405180910390f35b34156101a857600080fd5b6101b36004356107ce565b604051604081018290526060808252845460026000196101006001841615020190911604908201819052819060208201906080830190879080156102385780601f1061020d57610100808354040283529160200191610238565b820191906000526020600020905b81548152906001019060200180831161021b57829003601f168201915b50508381038252855460026000196101006001841615020190911604808252602090910190869080156102ac5780601f10610281576101008083540402835291602001916102ac565b820191906000526020600020905b81548152906001019060200180831161028f57829003601f168201915b50509550505050505060405180910390f35b34156102c957600080fd5b61018b6107e9565b34156102dc57600080fd5b61018b6107ef565b34156102ef57600080fd5b6102fa6004356107f5565b604051604081018390526060810182905260808082528554600260001961010060018416150201909116049082018190528190602082019060a0830190889080156103865780601f1061035b57610100808354040283529160200191610386565b820191906000526020600020905b81548152906001019060200180831161036957829003601f168201915b50508381038252865460026000196101006001841615020190911604808252602090910190879080156103fa5780601f106103cf576101008083540402835291602001916103fa565b820191906000526020600020905b8154815290600101906020018083116103dd57829003601f168201915b5050965050505050505060405180910390f35b341561041857600080fd5b6100e360046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528181529291906020840183838082843750949650509335935061081692505050565b60088180516104b59291602001906108ac565b50506001600955565b60006104c861092a565b600091505b600a548260ff16101561074157600c60008360ff16815260200190815260200160002060806040519081016040529081600082018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105955780601f1061056a57610100808354040283529160200191610595565b820191906000526020600020905b81548152906001019060200180831161057857829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106375780601f1061060c57610100808354040283529160200191610637565b820191906000526020600020905b81548152906001019060200180831161061a57829003601f168201915b50505050508152602001600282015481526020016003820154815250509050836040518082805190602001908083835b602083106106865780518252601f199092019160209182019101610667565b6001836020036101000a038019825116818451161790925250505091909101925060409150505190819003902081516040518082805190602001908083835b602083106106e45780518252601f1990920191602091820191016106c5565b6001836020036101000a038019825116818451161790925250505091909101925060409150505190819003902014156107365760ff82166000908152600c602052604090206001600390910155610741565b6001909101906104cd565b6060604051908101604090815284825260208083018790524282840152600b8054600181019091556000908152600d909152208151819080516107889291602001906108ac565b506020820151816001019080516107a39291602001906108ac565b50604082015160029091015550600b54600a5414156107c25760036009555b50505050565b600a5481565b600d6020526000908152604090206002810154600182019083565b600b5481565b60095481565b600c6020526000908152604090206002810154600382015460018301919084565b600260095410156108275760026009555b608060405190810160409081528482526020808301859052818301849052600060608401819052600a8054600181019091558152600c909152208151819080516108759291602001906108ac565b506020820151816001019080516108909291602001906108ac565b5060408201518160020155606082015160039091015550505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106108ed57805160ff191683800117855561091a565b8280016001018555821561091a579182015b8281111561091a5782518255916020019190600101906108ff565b5061092692915061095f565b5090565b60806040519081016040528061093e61097c565b815260200161094b61097c565b815260200160008152602001600081525090565b61097991905b808211156109265760008155600101610965565b90565b602060405190810160405260008152905600a165627a7a7230582080bea6975b2f3ea06376a6b0cc12ae194cdf30be2fc8a3db0d7eb435a888c9650029";

    protected LOrder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LOrder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> geneYd(String _ydCode) {
        Function function = new Function(
                "geneYd", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_ydCode)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> toReceipt(String _reNo, String _code) {
        Function function = new Function(
                "toReceipt", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_reNo), 
                new org.web3j.abi.datatypes.Utf8String(_code)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> ydSize() {
        Function function = new Function("ydSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> rcMap(BigInteger param0) {
        final Function function = new Function("rcMap", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, String, BigInteger>>(
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> rcSize() {
        Function function = new Function("rcSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> state() {
        Function function = new Function("state", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<String, String, BigInteger, BigInteger>> ydMap(BigInteger param0) {
        final Function function = new Function("ydMap", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, BigInteger>>(
                new Callable<Tuple4<String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> geneTs(String _code, String _carNo, BigInteger _weight) {
        Function function = new Function(
                "geneTs", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_code), 
                new org.web3j.abi.datatypes.Utf8String(_carNo), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<LOrder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _myCode, BigInteger _fee, String _goods, BigInteger _weight, String _from, String _to, String _go, String _wlCode) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_myCode), 
                new org.web3j.abi.datatypes.generated.Uint256(_fee), 
                new org.web3j.abi.datatypes.Utf8String(_goods), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.Address(_go), 
                new org.web3j.abi.datatypes.Utf8String(_wlCode)));
        return deployRemoteCall(LOrder.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<LOrder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _myCode, BigInteger _fee, String _goods, BigInteger _weight, String _from, String _to, String _go, String _wlCode) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_myCode), 
                new org.web3j.abi.datatypes.generated.Uint256(_fee), 
                new org.web3j.abi.datatypes.Utf8String(_goods), 
                new org.web3j.abi.datatypes.generated.Uint256(_weight), 
                new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.Address(_go), 
                new org.web3j.abi.datatypes.Utf8String(_wlCode)));
        return deployRemoteCall(LOrder.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static LOrder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LOrder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static LOrder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LOrder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
