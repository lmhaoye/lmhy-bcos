package com.lmhy.bcos.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
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
public class SUser extends Contract {
    private static final String BINARY = "606060405260008055341561001357600080fd5b610487806100226000396000f3006060604052600436106100775763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166356d88e27811461007c5780636d86b8a5146100a157806376f75e7f146100b45780637ff1126a146100e657806398aa8b4a14610149578063e08e67851461020e575b600080fd5b341561008757600080fd5b61008f61022d565b60405190815260200160405180910390f35b34156100ac57600080fd5b61008f610233565b34156100bf57600080fd5b6100ca60043561023a565b604051600160a060020a03909116815260200160405180910390f35b34156100f157600080fd5b61014760046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050600160a060020a038535169460200135935061026292505050565b005b341561015457600080fd5b61015f600435610318565b604051858152600160a060020a0385166020820152604081018490526080810182905260a0606082018181528454600260001961010060018416150201909116049183018290529060c0830190859080156101fb5780601f106101d0576101008083540402835291602001916101fb565b820191906000526020600020905b8154815290600101906020018083116101de57829003601f168201915b5050965050505050505060405180910390f35b341561021957600080fd5b610147600160a060020a0360043516610350565b60005481565b6001545b90565b600180548290811061024857fe5b600091825260209091200154600160a060020a0316905081565b60a0604051908101604090815260008054808452600160a060020a03861660208086019190915283850186905260608501889052426080860152600182018355908252600290522081518155602082015160018201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03929092169190911790556040820151816002015560608201518160030190805161030692916020019061039a565b50608082015160049091015550505050565b6002602081905260009182526040909120805460018201549282015460048301549193600160a060020a031692909160039091019085565b600180548082016103618382610418565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106103db57805160ff1916838001178555610408565b82800160010185558215610408579182015b828111156104085782518255916020019190600101906103ed565b50610414929150610441565b5090565b81548183558181151161043c5760008381526020902061043c918101908301610441565b505050565b61023791905b8082111561041457600081556001016104475600a165627a7a723058207e5d95ab266f8ec66fbd95641cd78c81a13b53dd856f5b1ec1ea132aa6a2c18e0029";

    protected SUser(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SUser(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> len() {
        Function function = new Function("len", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> orderSize() {
        Function function = new Function("orderSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> orderList(BigInteger param0) {
        Function function = new Function("orderList", 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> reg(String _name, String _address, BigInteger _cate) {
        Function function = new Function(
                "reg", 
                Arrays.<Type>asList(new Utf8String(_name),
                new Address(_address),
                new Uint256(_cate)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<BigInteger, String, BigInteger, String, BigInteger>> accmap(BigInteger param0) {
        final Function function = new Function("accmap", 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<BigInteger, String, BigInteger, String, BigInteger>>(
                new Callable<Tuple5<BigInteger, String, BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, String, BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<BigInteger, String, BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> saveOrder(String _od) {
        Function function = new Function(
                "saveOrder", 
                Arrays.<Type>asList(new Address(_od)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<SUser> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SUser.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SUser> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SUser.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SUser load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SUser(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SUser load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SUser(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
