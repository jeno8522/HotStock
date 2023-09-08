from theme_service import snapshot
import json

import openai


#FIXME : 실제 서비스에서는 사용할 수 없는 API 키 (테스트 이후 삭제되는 키)
__test_api_key = "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"
openai.api_key = __test_api_key

command = """
For each of the above keywords, compile related themes from the themes provided above and return them in JSON format.
At this time, you must not add any other words, and must answer only in JSON.
"""


def make_prompt(keywords:list) :
        prompt:str = "keywords : \n" + json.dumps(keywords, ensure_ascii=False) + "\n\n" + "themes : \n" + json.dumps(snapshot, ensure_ascii=False) + "\n\n" + command
        return prompt


class OpenAIService:
    
    def __init__(self):
        self.content = ""
        pass
    
    def get_themes_of_keywords(self, keywords:list): 
        content = make_prompt(keywords)
        self.content = content #테스트용
        messages = [{"role":"user", "content":content}]
        
        completion = openai.ChatCompletion.create(
            model="gpt-3.5-turbo-16k-0613",
            
            messages=messages
        )
        chat_response = completion.choices[0].message.content
        
        return chat_response
