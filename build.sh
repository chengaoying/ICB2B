#!/bin/bash

#build in jenkins 

#镜像tag
TAG=$JOB_NAME:`date +%y%m%d-%H-%M`

#使用maven编译打包工程
docker run --rm --name mvn  -v /mnt/maven:/root/.m2   \
-v /mnt/jenkins_home/workspace/$JOB_NAME:/usr/src/mvn -w /usr/src/mvn/ \
maven:3.3.3-jdk-8 mvn clean install -Dmaven.test.skip=true
 
#编译镜像
echo "image tag is:$TAG"
echo "workspace is:$WORKSPACE"
docker build -t  $TAG  $WORKSPACE/.
#docker push   $TAG
#docker rmi $TAG


#如果当前容器正在运行则停止并删除
if docker ps -a | grep -i $JOB_NAME; then
        docker stop $JOB_NAME
		docker rm $JOB_NAME
fi

#运行容器
docker run  -d  -p 8000:8080  --name $JOB_NAME  $TAG