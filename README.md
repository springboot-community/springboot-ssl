### `SpringBoot`开启`SSL`双向验证,以及使用`OkHttp`作为客户端访问

### CA,服务端,客户端的证书生成,以及签发过程

#### 创建CA证书并且导出公钥
    keytool -genkey -deststoretype pkcs12 -alias CA_ROOT -validity 3500 -keystore CA_ROOT.keystore -keyalg RSA -keysize 2048 -storepass 123456
    keytool -export -alias CA_ROOT -file CA_ROOT.cer -keystore CA_ROOT.keystore -storepass 123456

#### 创建客户端证书并且创建证书申请
    keytool -genkey -deststoretype pkcs12 -alias client -validity 365 -keystore client.keystore -keyalg RSA -keysize 2048 -storepass 123456
    keytool -certreq -alias client -file client.csr -keystore client.keystore -storepass 123456

#### 使用CA证书签发客户端证书
    keytool -gencert -alias CA_ROOT -infile client.csr -outfile client.cer -keystore CA_ROOT.keystore -storepass 123456

#### 创建服务端证书并且创建证书申请
    keytool -genkey -deststoretype pkcs12 -alias server -validity 365 -keystore server.keystore -keyalg RSA -keysize 2048 -storepass 123456
    keytool -certreq -alias server -file server.csr -keystore server.keystore -storepass 123456

#### 使用CA证书签发服务端证书
    keytool -gencert -alias CA_ROOT -infile server.csr -outfile server.cer -keystore CA_ROOT.keystore -storepass 123456

#### 客户端导入CA签发的证书,以及CA的公钥到keystore
    keytool -import -file CA_ROOT.cer -alias ca -keystore client.keystore -storepass 123456
    keytool -import -file ca_client.cer -alias client -keystore client.keystore -storepass 123456

#### 服务端导入CA签发的证书,以及CA的公钥到keystore
    keytool -import -file CA_ROOT.cer -alias ca -keystore server.keystore -storepass 123456
    keytool -import -file ca_server.cer -alias server -keystore server.keystore -storepass 123456

	