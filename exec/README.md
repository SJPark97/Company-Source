- 포트

  | port | 설명    |
  | ---- | ------- |
  | 22   | ssh     |
  | 80   | http    |
  | 443  | https   |
  | 3000 | nextjs  |
  | 8080 | spring  |
  | 8090 | jenkins |
  | 8306 | mysql   |
  | 8379 | redis   |
  | 9000 | mongodb |

- EC2

  ```bash
  # instance 생성
  # [<https://codemonkyu.tistory.com/entry/AWSEC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0-12>](<https://codemonkyu.tistory.com/entry/AWSEC2-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0-12>)
  
  # git 설치
  $ sudo apt install git
  
  # EC2에서 git 안될시 proxy 설정 초기화
  $ git config --global --unset http.proxy
  
  # java 11버전 다운로드
  $ sudo apt-get update
  $ sudo apt-get install -y openjdk-11-jdk
  
  # apt가 HTTPS를 통해 패키지를 설치할 수 있도록 셋업
  $ sudo apt-get update
  $ sudo apt-get install ca-certificates curl gnupg lsb-release
  
  # 도커의 공식 GPG 키 주가
  $ sudo mkdir -p /etc/apt/keyrings
  $ sudo curl -fsSL <https://download.docker.com/linux/ubuntu/gpg> | sudo gpg --dearmor --yes -o /etc/apt/keyrings/docker.gpg
  
  # 레포지토리 셋업
  $ echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] <https://download.docker.com/linux/ubuntu> $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  
  # 도커 엔진 설치
  $ sudo apt-get update
  $ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
  
  # 도커 설치 확인
  $ sudo docker run hello-world
  
  # 도커 권한 문제 발생시
  $ sudo groupadd docker
  $ sudo usermod -aG docker $USER
  $ newgrp docker
  ```

- Jenkins

  ~~~bash
  # [<https://hudi.blog/install-jenkins-with-docker-on-ec2/>](<https://hudi.blog/install-jenkins-with-docker-on-ec2/>)
  # 젠킨스 이미지 다운로드
  $ sudo docker pull jenkins/jenkins:lts
  
  # 젠킨스 컨테이너 띄우기 (8090포트)
  $ sudo docker run --name jenkins --restart=on-failure:5 -d -p 8090:8080 -v /jenkins:/var/jenkins_home -u root jenkins/jenkins:lts
  # 재시작시 
  $ sudo docker start jenkins
  
  # 위의 모든 과정을 좀 더 쉽게 쓰기 (docker-compose 사용) 밑에 자세하게 설명해놨음
  $ sudo apt install docker-compose
  ``` docker-compose.yml
  version: "3"
  services:
    jenkins:
      image: jenkins/jenkins:lts
      user: root
      volumes:
        - ./jenkins:/var/jenkins_home
      ports:
        - 8090:8080
  		restart: on-failure:5
  
  $ sudo docker-compose up -d
  
  
  
  # jenkins initial admin password
  
  $ sudo docker logs jenkins
  
  ------
  
  ------
  
  ------
  
  Jenkins initial setup is required. An admin user has been created and a password generated. Please use the following password to proceed to installation:
  
  ********************************     <<<< 이 부분이 password
  
  This may also be found at: /var/jenkins_home/secrets/initialAdminPassword
  
  ------
  
  ------
  
  ------
  
  # 타임존을 서울로 설정
  
  # Jenkins 관리 > Script Console
  
  System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', 'Asia/Seoul')
  ~~~

  

  # jenkins initial admin password

  ```bash
  # frontend
  ---clone 받기 코드---
  
  # frontend container 멈추기
  $ docker stop frontend
  
  # 이전 이미지 지우기
  $ docker rm frontend
  
  # docker 이미지 생성
  $ docker build -t frontend ./
  
  # frontend 컨테이너 띄우기
  $ docker run --name frontend -d -p 3000:3000 frontend
  
  # docker-compose 사용시 container stop and rm
  $ docker-compose down
  
  # docker image 삭제
  $ docker image rm frontend
  
  # docker-compose build도 함께 시작
  $ docker-compose up -d --build
  ```

  ```bash
  # frontend pipline
  
  pipeline {
      agent any
      stages {
          stage('clone') {
              steps {
                  git branch: 'frontend', credentialsId: 'gitlab', url: '<https://lab.ssafy.com/s08-final/S08P31B107.git>'
              }
          }
          
  
          stage('build') {
              steps {
                  dir('front') {
                      sh 'docker build -t frontend ./'
                  }
              }
          }
          
          stage('stop & rm before docker container') {
              steps {
                  script {
                      try {
                          sh '''
                              docker stop frontend
                              docker rm frontend
                          '''
                      } catch (e) {
                          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE')
                          {
                              sh 'exit 0'
                          }
                          echo 'rm TEST Fail!! But Im Running!'
                      }
                  }
              }
          }
          
          stage('deploy') {
              steps {
                  sh 'docker run --name frontend -d -p 3000:3000 frontend'
              }
          }
          
          stage('finish') {
              steps{
                  sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
              }
          }
      }
  }
  ```

  ```bash
  # backend
  ---clone 받기 코드---
  
  # backend container 멈추기
  $ docker stop backend
  
  # 이전 이미지 지우기
  $ docker rm backend
  
  # backend build 및 테스트
  $ chmod +x gradlew
  $ ./gradlew build
  $ ./gradlew test
  
  # docker 이미지 생성
  $ docker build -t backend ./
  
  # backend 컨테이너 띄우기
  $ docker run --name backend -d -p 8080:8080 backend
  
  # docker-compose 사용시 container stop and rm
  $ docker-compose down
  
  # docker image 삭제
  $ docker image rm backend
  
  # docker-compose build도 함께 시작
  $ docker-compose up -d --build
  ```

  ```bash
  # backend pipeline
  
  pipeline {
      agent any
      stages {
          stage('clone') {
              steps {
                  git branch: 'backend', credentialsId: 'gitlab', url: '<https://lab.ssafy.com/s08-final/S08P31B107.git>'
              }
          }
          
  
          stage('build') {
              steps {
                  dir('back/SourceCompany/SourceCompany') {
                      sh '''
                          chmod +x gradlew
                          ./gradlew build
                      '''
                  }
              }
          }
          
          stage('make image') {
              steps {
                  dir('back/SourceCompany/SourceCompany') {
                      sh 'docker build -t backend ./'
                  }
              }
          }
          
          stage('stop & rm before docker container') {
              steps {
                  script {
                      try {
                          sh '''
                              docker stop backend
                              docker rm backend
                          '''
                      } catch (e) {
                          catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE')
                          {
                              sh 'exit 0'
                          }
                          echo 'rm TEST Fail!! But Im Running!'
                      }
                  }
              }
          }
          
          stage('deploy') {
              steps {
                  sh 'docker run --name backend -d -p 8080:8080 backend'
              }
          }
          
          stage('finish') {
              steps{
                  sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
              }
          }
          
          
      }
  }
  ```

- DB 배포

  ```bash
  # redis
  # [<https://velog.io/@3210439/ec2%EC%97%90-docker-redis-%EC%84%A4%EC%B9%98>](<https://velog.io/@3210439/ec2%EC%97%90-docker-redis-%EC%84%A4%EC%B9%98>)
  
  # redis 이미지 받기
  $ docker image pull redis
  $ docker run -d --name redis -p 6379:6379 redis
  # redis 접속 bash가 안되면 sh로
  $ docker exec -it redis /bin/bash
  $ docker exec -it redis /bin/sh
  /data# redis-cli
  # 밑에 화면이 뜨면 접속완료된 것
  127.0.0.1:6369> 
  
  # mysql
  # [<https://mungiyo.tistory.com/23>](<https://mungiyo.tistory.com/23>)
  
  # 최신 MySQL 이미지를 가져온다
  $ docker pull mysql:latest
  
  # 컨테이너가 삭제될때 같이 데이터가 삭제되기 때문에 명시적 volume 설정
  $ docker volume create mysql-volume
  
  # volume 확인
  $ docker volume ls
  
  # 생성한 volume을 컨테이너에 마운팅하여 실행
  $ docker run -d --name mysql -p 8306:3306 -v mysql-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=comso107 mysql:latest
  
  # bash 쉘로 접속
  $ docker exec -it mysql bash
  
  # MySQL 서버 접속
  $ mysql -u root -p
  # password 입력
  
  # USER 생성, '%'는 모든 IP에서 접속 허용
  mysql> CREATE USER comso@'%' identified by 'B107';
  # USER에 모든 권한 부여
  
  mysql> GRANT ALL PRIVILEGES ON *.* to comso@'%';
  # 변경사항 적용
  
  mysql> FLUSH PRIVILEGES;
  # 나가기
  mysql> exit;
  
  # MongoDB
  # [<https://velog.io/@ohju96/%EC%86%8C%EA%B2%BD%EA%B4%80-AWS-EC2-Ubuntu-20.04%EC%97%90%EC%84%9C-Docker%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%B4%EC%84%9C-MariaDB-MongoDB-%EA%B5%AC%EC%B6%95-%ED%9B%84-%EC%97%B0%EB%8F%99>](<https://velog.io/@ohju96/%EC%86%8C%EA%B2%BD%EA%B4%80-AWS-EC2-Ubuntu-20.04%EC%97%90%EC%84%9C-Docker%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%B4%EC%84%9C-MariaDB-MongoDB-%EA%B5%AC%EC%B6%95-%ED%9B%84-%EC%97%B0%EB%8F%99>)
  
  # 최신 MongoDB 이미지를 가져온다
  $ docker pull mongo:latest
  
  # MongoDB 컨테이너 시작
  $ docker run -d --name MongoDB -p 9000:27017 -e MONGO_INITDB_ROOT_USERNAME=comso -e MONGO_INITDB_ROOT_PASSWORD=B107 mongo:latest
  
  # bash 쉘로 접속
  $ docker exec -it MongDB bash
  
  # 로그인
  /# mongosh -u comso -p B107
  # 6.0 이상 버전에서는 mongo커맨드가 사용되지 않고 mongosh 커맨드를 사용해야 한다.
  
  # 밑에서 docker-compose로 jenkins랑 같이 관리했다.
  ```

- EC2 램 부족시 swap 메모리 할당

  ```bash
  # [<https://www.zinnunkebi.com/aws-t2-micro-swap-allocate/>](<https://www.zinnunkebi.com/aws-t2-micro-swap-allocate/>)
  
  ## swap 메모리와 디스크 여유 공간, 사용량 확인
  $ free
                 total        used        free      shared  buff/cache   available
  Mem:          989388      256988       66160         976      666240      547936
  Swap:              0           0           0
  
  $ df
  Filesystem     1K-blocks    Used Available Use% Mounted on
  /dev/root       15054340 4014884  11023072  27% /
  tmpfs             494692       0    494692   0% /dev/shm
  tmpfs             197880     968    196912   1% /run
  tmpfs               5120       0      5120   0% /run/lock
  /dev/xvda15       106858    6182    100677   6% /boot/efi
  tmpfs              98936       4     98932   1% /run/user/1000
  
  # 2G(bs*count) SSD 메모리를 RAM처럼 쓸 수 있도록 swap 메모리 할당
  
  $ sudo dd if=/dev/zero of=/swapfile bs=64M count=32
  32+0 records in
  32+0 records out
  2147483648 bytes (2.1 GB, 2.0 GiB) copied, 31.1836 s, 68.9 MB/s
  
  # swap으로 동작하도록 만든다.
  $ sudo chmod 600 /swapfile
  # swap 설정 확인
  $ sudo mkswap /swapfile
  Setting up swapspace version 1, size = 2 GiB (2147479552 bytes)
  no label, UUID=1fe4d956-8a00-4e67-a3a0-b083d7f6c1d4
  
  # swap 활성화
  $ sudo swapon /swapfile
  $ sudo swapon -s
  Filename                                Type            Size            Used            Priority
  /swapfile                               file            2097148         0               -2
  
  # 재부팅 후에도 계속 사용하려면 수정
  # 라고 하는데 구조가 달라서 코드를 추가하지 못했다.
  # 마지막 줄에 그냥 무지성으로 추가하면 된다.
  $ sudo vi /etc/fstab
  ...중간생략...
  UUID=f41e390f-835b-4223-a9bb-9b45984ddf8d / xfs defaults 0 0
  # 마지막 라인에 추가
  /swapfile swap swap defaults 0 0
  
  # 결과
  $ free
  total        used        free      shared  buff/cache   available
  Mem:          989388      274144       74364         976      640880      524780
  Swap:        2097148           0     2097148
  
  $df
  Filesystem     1K-blocks    Used Available Use% Mounted on
  /dev/root       15054340 6112008   8925948  41% /
  tmpfs             494692       0    494692   0% /dev/shm
  tmpfs             197880     968    196912   1% /run
  tmpfs               5120       0      5120   0% /run/lock
  /dev/xvda15       106858    6182    100677   6% /boot/efi
  tmpfs              98936       4     98932   1% /run/user/1000
  
  # 마지막 부분과 상관없이 증설이 완료되었다.
  # 더 좋은 ec2 instance를 생성해서 이 부분은 새로운 instance에서는 하지 않았다.
  ```

- nginx

  ```bash
  # nginx 설치
  $ sudo apt update
  $ sudo apt install nginx
  
  # nginx 버전 확인
  $ nginx -v
  
  # HTTP로 접속할 수 있도록 인바운드 규칙 추가 (80포트)
  # nginx 사용을 위한 dafault 파일 수정
  $ cd /etc/nginx/sites-available/
  $ sudo vim default
  
  # nginx 시작
  $ sudo systemctl start nginx
  ```

  ```bash
  # nginx default
  
  server {
          listen 80;
          listen [::]:80;
  
          root /var/www/html;
  
          index index.html index.htm index.nginx-debian.html;
  
          server_name company-source.com;
          return 301 https://company-source.com$request_uri;
  }
  
  upstream my-backend {
    		server backend-1:8080;
    		server backend-2:8080;
    		server backend-3:8080;
  }
  
  server {
          listen 443 ssl;
          server_name company-source.com;
  
          ssl_certificate /etc/letsencrypt/live/company-source.com/fullchain.pem;
          ssl_certificate_key /etc/letsencrypt/live/company-source.com/privkey.pem;
  
          location / {
                  proxy_pass http://localhost:3000;
          }
  
          location /api {
                  proxy_pass http://my-backend;
                  proxy_set_header Host $host;
                  proxy_set_header X-Real-IP $remote_addr;
                  proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          }
  
  }
  ```

- Dockerfile

  ```docker
  # frontend
  
  # basic 이미지 생성
  FROM node:14-alpine
  
  # 작업 디렉토리 고정
  WORKDIR /app
  
  # package.json과 package-lock.json 복사
  COPY package*.json ./
  
  # 패키지 설치
  RUN npm install --production
  
  # docker 이미지로 복사
  COPY . .
  
  # Build the application
  RUN npm run build
  
  # 3000번 포트로 열기
  EXPOSE 3000
  ENV PORT 3000
  
  # 시작 명령어
  CMD ["npm", "start"]
  ```

  ```docker
  # backend
  
  # open jdk java11 버전의 환경
  FROM openjdk:11-jdk
  
  # JAR_FILE 변수 정의 -> 기본적으로 jar file이 2개이기 때문에 이름을 특정해야함
  ARG JAR_FILE=./build/libs/SourceCompany-0.0.1-SNAPSHOT.jar
  
  # JAR 파일 메인 디렉토리에 복사
  COPY ${JAR_FILE} app.jar
  
  # 시스템 진입점 정의
  ENTRYPOINT ["java","-jar","/app.jar"]
  ```

- docker-compose

  ```bash
  # docker-compose.yml jenkins mysql mongodb redis
  
  version: '3'
  
  services:
    jenkins:
      image: jenkins/jenkins:lts
      user: root
      container_name: jenkins
      volumes:
        - jenkins-volume:/var/jenkins_home
      ports:
        - 8090:8080
      restart: on-failure:5
  
    mysql:
      image: mysql:latest
      container_name: mysql
      environment:
        MYSQL_ROOT_PASSWORD: comso107
      volumes:
        - mysql-volume:/var/lib/mysql
      ports:
        - 8306:3306
  
    mongodb:
      image: mongo:latest
      container_name: mongodb
      environment:
        MONGO_INITDB_ROOT_USERNAME: comso
        MONGO_INITDB_ROOT_PASSWORD: B107
      volumes:
        - mongodb-volume:/data/db
      ports:
        - 9000:27017
  
    redis:
      image: redis
      container_name: redis
      command: redis-server --requirepass B107
      volumes:
        - redis-volume:/data
      ports:
        - 8379:6379
  
  volumes:
    jenkins-volume:
      external: true
    mysql-volume:
      external: true
    mongodb-volume:
      external: true
    redis-volume:
      external: true
  ```

  ```bash
  # docker-compose.yml frontend
  
  version: '3'
  services:
      frontend:
          build: .
          container_name: frontend
          ports:
              - "3000:3000"
          restart: always
  ```

  ```bash
  # docker-compose.yml backend
  
  version: '3'
  services:
      backend_1:
          build: .
          container_name: backend
          ports:
              - "8080:8080"
          networks:
              - backend-network
          restart: always
      backend_2:
          build: .
          container_name: backend
          ports:
              - "8080:8080"
          networks:
              - backend-network
          restart: always
      backend_3:
          build: .
          container_name: backend
          ports:
              - "8080:8080"
          networks:
              - backend-network
          restart: always
  
  networks:
    backend-network:
      driver: bridge
  ```

- kubernetes

  ```bash
  # [<https://velog.io/@dry8r3ad/Kubernetes-Cluster-Installation>](<https://velog.io/@dry8r3ad/Kubernetes-Cluster-Installation>)
  ```