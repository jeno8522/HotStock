from service.theme_service import snapshot
import json

import openai


#FIXME : 실제 서비스에서는 사용할 수 없는 API 키 (테스트 이후 삭제되는 키)
__test_api_key = "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"
openai.api_key = __test_api_key

command = """
위 keywords, themes에 대하여 분석할거야.
각 keyword에 대하여, 관련이 있는 모든 theme들을 (단, keyword는 theme가 아니다) 엮어서 JSON 형태로 반환해줘.
이 때, 다른 말은 하나도 더하면 안되고, JSON으로만 대답해줘야해.
"""


def make_prompt(keywords:list) :
        prompt:str = "keywords : \n" + json.dumps(keywords, ensure_ascii=False) + "\n\n" + "themes : \n" + json.dumps(snapshot, ensure_ascii=False) + "\n\n" + command
        return prompt


class OpenAIService:
    
    def __init__(self):
        self.content = ""#TODO : 삭제해야할 코드, 테스트를 위한 log 확인용

    
    def get_themes_of_keywords(self, keywords:list): 
        content = make_prompt(keywords)
        messages = [{"role":"user", "content":content}]
        
        completion = openai.ChatCompletion.create(
            model="gpt-3.5-turbo-16k-0613",
            
            messages=messages
        )
        chat_response = completion.choices[0].message.content
        
        self.content = content #TODO : 삭제해야할 코드, 테스트를 위한 log 확인용
        return chat_response
