SHELL := /bin/bash

build:
	rm -rf docker/jars/*.jar
	./mvnw clean package
run: build
	java -jar `ls target/*.jar`

rm-image:
	docker rm nori-analyzer-api || exit 0
	docker rmi nori-analyzer-api:`git log -1 --pretty=%h` || exit 0
	
build-image: build rm-image
	mkdir -p docker/jars
	mkdir -p docker/userdict
	cp -a target/*.jar docker/jars/
	cp -a userdict/* docker/userdict/
	docker build -t nori-analyzer-api:`git log -1 --pretty=%h` ./docker

launch-image: build-image
	mkdir -p `pwd`/coll_dir
	docker run -it --name nori-analyzer-api -p 8080:880 --entrypoint sh nori-analyzer-api:`git log -1 --pretty=%h`

run-image: build-image
	docker run -it --name nori-analyzer-api -p 8080:8080 nori-analyzer-api:`git log -1 --pretty=%h`

tag-image: build-image
ifdef TAG
	docker tag nori-analyzer-api:`git log -1 --pretty=%h` darimuri/nori-analyzer-api:${TAG}
else
	@echo "TAG is required"
endif

push-image: tag-image
ifdef TAG
	docker push darimuri/nori-analyzer-api:${TAG}
else
	@echo "TAG is required"
endif
