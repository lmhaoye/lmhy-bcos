echo "wait";
solc src/SUser.sol --bin --abi --optimize -o output/  --overwrite

solc src/SOrder.sol --bin --abi --optimize -o output/ --overwrite

solc src/LUser.sol --bin --abi --optimize -o output/ --overwrite

solc src/LOrder.sol --bin --abi --optimize -o output/ --overwrite