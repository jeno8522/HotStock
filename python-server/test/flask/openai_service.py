from theme_service import snapshot
import json

import openai


#FIXME : 실제 서비스에서는 사용할 수 없는 API 키 (테스트 이후 삭제되는 키)
__test_api_key = "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"
openai.api_key = __test_api_key

command = """
위 keywords에 대하여, 관련이 있는 모든 theme을 엮어서 JSON 형태로 반환해줘.
이 때, 다른 말은 하나도 더하면 안되고, JSON으로만 대답해줘야해.
해당 키워드에 엮을만한 테마가 없는 경우엔 keyword : [] 형태로 반환해줘.
"""


def make_prompt(keywords:list) :
        prompt:str = "keywords : \n" + json.dumps(keywords, ensure_ascii=False) + "\n\n" + "themes : \n" + json.dumps(snapshot, ensure_ascii=False) + "\n\n" + command
        return prompt


class OpenAIService:
    
    def __init__(self):
        pass
    
    def get_themes_of_keywords(self, keywords:list):  
        content = make_prompt(keywords)
        messages = [{"role":"user", "content":content}]
        
        completion = openai.ChatCompletion.create(
            # model="gpt-3.5-turbo",
            model="gpt-4-0613",
            
            messages=messages
        )
        chat_response = completion.choices[0].message.content
        
        return chat_response
