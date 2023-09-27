# 기본 파이썬 이미지 사용
FROM python:3.11.5-slim

#작업 디렉토리 설정
WORKDIR /app

# 필요한 패키지 설치
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt

# 애플리케이션 코드 복사
COPY . .


# 포트 5000 열기
EXPOSE 5000


# 애플리케이션 실행
# 1프로세스 2스레드
CMD ["gunicorn", "--timeout", "600", "-w", "1", "--threads", "2", "-b", "0.0.0.0:5000", "run:app"]

