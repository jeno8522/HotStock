import os
import json
from http import HTTPStatus
from flask import Flask, jsonify, request, Response
from flask_cors import CORS

from service.keyword_service import KeywordService
from service.theme_service import ThemeService
from service.openai_service import OpenAIService

keyword_service = KeywordService()
theme_service = ThemeService()
openai_service = OpenAIService()

import test_code.test as test_small 
import test_code.test_big as test_big 


# Flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app)

def to_json(data) : return json.dumps(data, ensure_ascii=False)

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
#TODO : 테스트 이후 삭제 예정
@app.route('/keyword/<content>', methods=['GET'])
def get_keyword(content):
    data: dict = keyword_service.get_keyword(content) # { keyword : weight }
    
    response = Response(to_json(data), content_type='application/json; charset=utf-8')
    return response


# text 여러 개를 받아 키워드 추출
@app.route('/keyword', methods=['POST'])
def get_keywords():
    data: dict = keyword_service.get_keywords(list(request.json['texts'])) # { keyword : weight }
    
    response = Response(to_json(data), content_type='application/json; charset=utf-8')
    return response


#theme의 정보를 크롤링하여 가져오는 api
#TODO : 현재는 메모리에 들고 있는 걸 주지만, 추후 실제로 전달하기
@app.route('/theme', methods=['GET'])
def get_themes():
    data: dict = theme_service.get_theme_infos() # { theme_name : stocks }
    
    response = Response(to_json(data), content_type='application/json; charset=utf-8')
    return response


#keyword list를 받아 각 키워드와 관련된 themes를 가져오는 api
@app.route('/theme', methods=['POST'])
def get_keywords_themes():
    keywords = list(request.json['keywords'])
    data: dict = openai_service.get_themes_of_keywords(keywords) # { keyword : [theme] }
    
    response = Response(to_json(data), content_type='application/json; charset=utf-8')
    return response


@app.route('/test/small', methods=['GET'])
def test1():
    return test_small.test()


@app.route('/test/big', methods=['GET'])
def test2():
    return test_big.test()


if __name__ == '__main__':
    # 코드 수정시 자동 반영
    app.run(debug=True)