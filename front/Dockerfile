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