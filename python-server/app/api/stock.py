from flask import request, Response
from flask_restx import Resource, Namespace, fields
from app.service.stock_service import StockService
from app.util.json_util import to_json

stock_service = StockService()

ns = Namespace('stock', description='Stock API')

item_code = fields.String

@ns.route('/')
class Stocks(Resource):
    @ns.expect([item_code])
    @ns.doc(description='payload\'s ["string"] is in the format [item_code]')
    def post(self):
        data: list = list(request.json)
        response_data:list = stock_service.get_stock_infos(data)
        response = Response(
            to_json(response_data), content_type='application/json; charset=utf-8')
        return response
    
    # @ns.doc(description='get all stocks')
    # def get(self):
    #     response_data:list = stock_service.get_stock_infos()
    #     response = Response(
    #         to_json(response_data), content_type='application/json; charset=utf-8')
    #     return response
    
@ns.route('/<item_code>')
class Stock(Resource):
    def get(self, item_code):
        response_data:dict = stock_service.get_stock_info(item_code)
        response = Response(
            to_json(response_data), content_type='application/json; charset=utf-8')
        return response
    
from app import api_root
api_root.add_namespace(ns)