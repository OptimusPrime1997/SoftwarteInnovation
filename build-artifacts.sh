#!/usr/bin/env bash
build_dirs=("config-center-service" "buyer-info-service" "item-info-service"
"layer1-buyer-seller-service"
"order-service" "spring-cloud-eureka-service" "store-service" "home-page-service")
cd platinum_rs_beans
mvn clean install -DSkipTests=True
cd ..
for value in ${build_dirs[@]}
do
	echo "start to build $value"
	cd $value
	mvn clean package -Dmaven.test.skip=true
	cd ..
done

if [ ! -e artifacts ]
then
    mkdir artifacts
fi

cp *service/target/*.jar artifacts/
