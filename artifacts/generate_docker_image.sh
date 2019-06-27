all_jars=$(ls *.jar)


docker_template(){
	jar_name=$1
	if [ -e Dockerfile ]
	then
		rm -f Dockerfile
	fi
	echo "FROM java:8" >> Dockerfile
	echo "VOLUME /tmp" >> Dockerfile
	echo "ADD $jar_name /app.jar" >> Dockerfile
	echo 'ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]' >> Dockerfile
}


for value in ${all_jars[@]}
do
	file_name=${value%-0.0.1-SNAPSHOT.jar}
	if [ ! -e $file_name ]
	then
		mkdir $file_name
	fi
	cp $value ./$file_name/
	cd $file_name
	docker_template $value
	docker build -t $file_name .
	cd ..
done


