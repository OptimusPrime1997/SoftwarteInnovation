
all_jars=$(ls *service/target/*.jar)

for value in ${all_jars[@]}
do
	echo "start to build $value"
	service_name=${value##*/}
	service_name=${service_name%-0.0.1-SNAPSHOT.jar}
	cp $value ./artifacts/$service_name/
	docker build -t yyf15520065137/$service_name ./artifacts/$service_name
	docker push yyf15520065137/$service_name	
	rm -f ./artifacts/$service_name/$value
done
