from flask import request, Response
from flask_restx import Resource, Namespace, fields
from app.service.news_service import NewsService
from app.util.json_util import to_json

news_service = NewsService()

ns = Namespace('news', description='News API')

item_code = fields.String

@ns.route('/<media_company_id>/<article_id>')
class News(Resource):
    def get(self,media_company_id, article_id):
        response_data:list = news_service.get_news(media_company_id, article_id)
        response = Response(
            to_json(response_data), content_type='application/json; charset=utf-8')
        return response
    
from app import api_root
api_root.add_namespace(ns)