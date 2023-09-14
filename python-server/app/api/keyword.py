from flask import request, Response
from flask_restx import Resource, Namespace, fields
from app.service.keyword_service import KeywordService
from app.util.json_util import to_json

keyword_service = KeywordService()

ns = Namespace('keyword', description='Keyword API')
news_item = fields.List(fields.String, description='News data in the format [news_id, news_title, news_content]')


@ns.route('/')
class Keywords(Resource):
    @ns.expect([news_item])
    @ns.doc(description='payload\'s ["string"] is in the format [news_id, news_title, news_content]', responses={200: 'Success', 400: 'Invalid Argument'})
    def post(self):
        data: list = list(request.json)
        response_data : dict = keyword_service.get_keywords(data)
        response = Response(
            to_json(response_data, "keywords"), content_type='application/json; charset=utf-8')
        return response


#테스트용도, 실제로 사용하지 않을 예정
@ns.route('/<news_id>/<news_title>/<news_content>')
class Keyword(Resource):
    def get(self, news_id, news_title, news_content):
        response_data : dict = keyword_service.get_keyword(news_id, news_title, news_content)
        response = Response(
            to_json(response_data, "keywords"), content_type='application/json; charset=utf-8')
        return response

from app import api_root
api_root.add_namespace(ns)