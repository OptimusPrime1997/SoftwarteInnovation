#!/usr/bin/env bash
build_dirs=("buyer-info-service" "item-info-service" "layer1-buyer-seller-service" "order-service" "spring-cloud-eureka-service" "store-service")
cd platinum_rs_beans
mvn clean install
cd ..
for value in ${build_dirs[@]}
do
	echo "start to build $value"
	cd $value
	mvn clean package -DSkipTests=True
	cd ..
done

mkdir artifacts

cp *service/target/*.jar artifacts/
