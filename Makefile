SHELL := /bin/bash

build:
	./mvnw package

build-image: build
	mkdir -p docekr/jars
	cp -a target/*.jar docekr/jars/
	docker build -t nori-analyzer-api:latest ./docekr

run-image: build-image
	docker run -it --name nori-analyzer-api -p 8080:8080 nori-analyzer-api:latest

sh-image: build-image
	docker run -it --name nori-analyzer-api --entrypoint sh nori-analyzer-api:latest

tag-image: build-image
ifdef TAG
	docker tag nori-analyzer-api:latest darimuri/nori-analyzer-api:${TAG}
else
	@echo "TAG is required"
endif

push-image: tag-image
ifdef TAG
	docker push darimuri/nori-analyzer-api:${TAG}
else
	@echo "TAG is required"
endif

rm-image:
	docker rm nori-analyzer-api
	docker rmi nori-analyzer-api:latest