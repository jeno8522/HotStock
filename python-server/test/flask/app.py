import os
import json
from http import HTTPStatus
from flask import Flask, jsonify, request, Response
from flask_cors import CORS


from keyword_service import KeywordService
from theme_service import ThemeService
from openai_service import OpenAIService

keyword_service = KeywordService()
theme_service = ThemeService()
openai_service = OpenAIService()

# Flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app)


# 접속 url 설정
@app.route('/', methods=['GET', 'POST'])
def visit():
    if request.method == 'GET':
        res = {'msg': 'This is GET Home'}
        return jsonify(res)

    if request.method == 'POST':
        res = {'msg': 'This is POST Home'}
        return jsonify(res)


# 테스트를 위해 get 형태로 text 하나에서 키워드 추출
@app.route('/keyword/<content>', methods=['GET'])
def get_keyword(content):
    data: dict = keyword_service.get_keyword(content) # { keyword : weight }
    
    response = Response(json.dumps(data, ensure_ascii=False), content_type='application/json; charset=utf-8')
    return response


# text 여러 개를 받아 키워드 추출
@app.route('/keyword', methods=['POST'])
def get_keywords():
    data: dict = keyword_service.get_keywords(list(request.json['texts'])) # { keyword : weight }
    
    response = Response(json.dumps(data, ensure_ascii=False), content_type='application/json; charset=utf-8')
    return response


#theme의 정보를 크롤링하여 가져오는 api
@app.route('/theme', methods=['GET'])
def get_themes():
    data: dict = theme_service.get_theme_infos() # { theme_name : stocks }
    
    response = Response(json.dumps(data, ensure_ascii=False), content_type='application/json; charset=utf-8')
    return response


#keyword list를 받아 각 키워드와 관련된 themes를 가져오는 api
@app.route('/theme', methods=['POST'])
def get_keywords_themes():
    keywords = list(request.json['keywords'])
    data: dict = openai_service.get_themes_of_keywords(keywords) # { keyword : [theme] }
    
    response = Response(json.dumps(data, ensure_ascii=False), content_type='application/json; charset=utf-8')
    return response


if __name__ == '__main__':
    # 코드 수정시 자동 반영
    app.run(debug=True)