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
import org.web3j.tuples.generated.Tuple3;
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
public class LUser extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6106878061001e6000396000f3006060604052600436106100775763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416633d1c40aa811461007c57806375df00571461009d57806383c8a27c1461014b5780638798fd88146101705780638df5433e146101d1578063bfe5899814610203575b600080fd5b341561008757600080fd5b61009b600160a060020a03600435166102b9565b005b34156100a857600080fd5b6100bc600160a060020a0360043516610304565b604051600160a060020a03841681526040810182905260606020820181815290820184818151815260200191508051906020019080838360005b8381101561010e5780820151838201526020016100f6565b50505050905090810190601f16801561013b5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b341561015657600080fd5b61015e610471565b60405190815260200160405180910390f35b341561017b57600080fd5b61009b60048035600160a060020a03169060446024803590810190830135806020601f82018190048102016040519081016040528181529291906020840183838082843750949650509335935061047792505050565b34156101dc57600080fd5b6101e7600435610509565b604051600160a060020a03909116815260200160405180910390f35b341561020e57600080fd5b610219600435610531565b604051600160a060020a038416815260408101829052606060208201818152845460026000196101006001841615020190911604918301829052906080830190859080156102a85780601f1061027d576101008083540402835291602001916102a8565b820191906000526020600020905b81548152906001019060200180831161028b57829003601f168201915b505094505050505060405180910390f35b60008054600181016102cb838261055d565b506000918252602090912001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b600061030e610586565b6000610318610598565b60005b6001548160ff1610156104565760ff8116600090815260026020526040902054600160a060020a038781169116141561044e57600260008260ff168152602001908152602001600020606060405190810160405290816000820160009054906101000a9004600160a060020a0316600160a060020a0316600160a060020a03168152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104385780601f1061040d57610100808354040283529160200191610438565b820191906000526020600020905b81548152906001019060200180831161041b57829003601f168201915b5050505050815260200160028201548152505091505b60010161031b565b81518260200151836040015191989097509095509350505050565b60015481565b60606040519081016040908152600160a060020a038516825260208083018590528183018490526001805480820190915560009081526002909152208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03919091161781556020820151816001019080516104f79291602001906105c0565b50604082015160029091015550505050565b600080548290811061051757fe5b600091825260209091200154600160a060020a0316905081565b6002602081905260009182526040909120805491810154600160a060020a039092169160019091019083565b8154818355818115116105815760008381526020902061058191810190830161063e565b505050565b60206040519081016040526000815290565b606060405190810160405260008152602081016105b3610586565b8152602001600081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061060157805160ff191683800117855561062e565b8280016001018555821561062e579182015b8281111561062e578251825591602001919060010190610613565b5061063a92915061063e565b5090565b61065891905b8082111561063a5760008155600101610644565b905600a165627a7a723058203dcabd06ac9dff636c66950b2715cbd4db08fa0ecc3e88ecf356d4b4c29e7fea0029";

    protected LUser(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LUser(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> addOrder(String adr) {
        Function function = new Function(
                "addOrder", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(adr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> getByAdr(String adr) {
        final Function function = new Function("getByAdr", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(adr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteCall<BigInteger> umapSize() {
        Function function = new Function("umapSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> add(String _adr, String _name, BigInteger _ty) {
        Function function = new Function(
                "add", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_adr), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_ty)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> los(BigInteger param0) {
        Function function = new Function("los", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple3<String, String, BigInteger>> umap(BigInteger param0) {
        final Function function = new Function("umap", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
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

    public static RemoteCall<LUser> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LUser.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<LUser> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(LUser.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static LUser load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LUser(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static LUser load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LUser(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
