import json
import urllib.request
from collections import defaultdict

class StockService:
    
    def __init__(self):
        pass
    
    
    def get_stock_info(self, item_code: str) -> dict:
        url = "https://api.finance.naver.com/service/itemSummary.nhn?itemcode=%s"%(item_code)
        #urllib.request를 통해 링크의 결과를 가져옵니다.
        raw_data: str = urllib.request.urlopen(url).read() #json

        try :
            data: dict = json.loads(raw_data) #dict 구조로 변경
        except :
            data = defaultdict(lambda: "-1") #만약 상장 폐지 등으로 정보를 가져오지 못했다면 모든 반환값을 -1로 반환.
            
            
        resp = dict()
        resp.update(
                {"code" : str(item_code),
                "market_sum": str(data["marketSum"]),
                "price_now": str(data["now"]),
                "price_rate": str(data["rate"]),
                "price_diff": str(data["diff"]),
                "price_high": str(data["high"]),
                "price_low": str(data["low"]),
                "amount": str(data["amount"])}
            )
        return resp


    def get_stock_infos(self, item_codes: list) -> list:
        resp = []
        
        for item_code in item_codes:
            resp.append(self.get_stock_info(item_code))
            
        return resp
