from flask import request, Response
from flask_restx import Resource, Namespace, fields
from app.service.theme_service import ThemeService
from app.util.json_util import to_json

theme_service = ThemeService()

ns = Namespace('theme', description='Keyword API')

keyword_item = fields.String

# keyword list를 받아 각 키워드와 관련된 themes를 가져오는 api
@ns.route('/')
class KeywordsThemes(Resource):
    @ns.expect([keyword_item])
    @ns.doc(description='payload\'s ["string"] is in the format [keyword]')
    def post(self):
        data: list = list(request.json)
        response_data:dict = theme_service.get_themes_of_keywords(data)
        response = Response(
            to_json(response_data, "keywords_themes"), content_type='application/json; charset=utf-8')
        return response
    
    
    # theme의 정보를 크롤링하여 가져오는 api
    # XXX : 현재 사용하지 않음 추후 사용할 예정
    @ns.doc(description='get all themes')
    def get(self):
        response_data:dict = theme_service.get_theme_infos()
        response = Response(
            to_json(response_data), content_type='application/json; charset=utf-8')
        return response
    
@ns.route('/gpt')
class TempKeywordsThemes(Resource):
    @ns.expect([keyword_item])
    @ns.doc(description='payload\'s ["string"] is in the format [keyword]')
    def post(self):
        data: list = list(request.json)
        response_data:dict = theme_service.get_themes_of_keywords(data,'gpt')
        response = Response(
            to_json(response_data, "keywords_themes"), content_type='application/json; charset=utf-8')
        return response

from app import api_root
api_root.add_namespace(ns)