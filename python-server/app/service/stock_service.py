import json
import urllib.request


class StockService:
    
    def __init__(self):
        pass
    
    
    def get_stock_info(self, item_code: str) -> dict:

        url = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=%s"%(item_code)
        #urllib.request를 통해 링크의 결과를 가져옵니다.
        raw_data: str = urllib.request.urlopen(url).read() #json
        data: dict = json.loads(raw_data) #dict 구조로 변경
        
        resp = dict()
        resp["code"] = item_code
        resp["market_sum"] = str(data["marketSum"])
        resp["price_now"] = str(data["now"])
        resp["price_rate"] = str(data["rate"])
        resp["price_high"] = str(data["high"])
        resp["price_low"] = str(data["low"])
        resp["amount"] = str(data["amount"])

        return resp


    def get_stock_infos(self, item_codes: list) -> list:
        resp = []
        
        for item_code in item_codes:
            resp.append(self.get_stock_info(item_code))
            
        return resp
