from flask import request, Response
from app import app
from ..service.keyword_service import KeywordService
from ..util.json_util import to_json

keyword_service = KeywordService()


# text 여러 개를 받아 키워드 추출
@app.route('/keyword', methods=['POST'])
def get_keywords():
    data: dict = keyword_service.get_keywords(
        list(request.json['texts']))  # { keyword : weight }

    response = Response(
        to_json(data), content_type='application/json; charset=utf-8')
    return response


# 테스트를 위해 get 형태로 text 하나에서 키워드 추출
# XXX : 사용하지 않음. 테스트용
@app.route('/keyword/<content>', methods=['GET'])
def get_keyword(content):
    data: dict = keyword_service.get_keyword(content)  # { keyword : weight }

    response = Response(
        to_json(data), content_type='application/json; charset=utf-8')
    return response
