from flask import Flask
from flask_cors import CORS
from flask_restx import Api


# Flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app)
api_root = Api(app, version='0.1.0 alpha', title="Hot-Stock's API Server", description="Hot-Stock's API Server", doc='/')


# API 라우트 임포트
from .api import keyword, theme, stock
