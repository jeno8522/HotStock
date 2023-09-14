from flask import Flask
from flask_cors import CORS

# Flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app)

@app.route('/', methods=['GET'])
def index():
    return '처음 화면입니다.'


# API 라우트 임포트
from .api import keyword, theme
