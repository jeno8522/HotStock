from flask import request, Response
from app import app
from ..service.theme_service import ThemeService
from ..util.json_util import to_json

theme_service = ThemeService()


# keyword list를 받아 각 키워드와 관련된 themes를 가져오는 api
@app.route('/theme', methods=['POST'])
def get_keywords_themes():
    keywords = list(request.json['keywords'])
    data: dict = theme_service.get_themes_of_keywords(
        keywords)  # { keyword : [theme] }

    response = Response(
        to_json(data), content_type='application/json; charset=utf-8')
    return response


# theme의 정보를 크롤링하여 가져오는 api
# XXX : 현재는 사용하지 않음. 추후 사용할 예정
@app.route('/theme', methods=['GET'])
def get_themes():
    data: dict = theme_service.get_theme_infos()  # { theme_name : stocks }

    response = Response(
        to_json(data), content_type='application/json; charset=utf-8')
    return response
