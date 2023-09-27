import json
import openai

# FIXME : 실제 서비스에서는 사용할 수 없는 API 키 (테스트 이후 삭제되는 키)
__test_api_key = "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"

openai.api_key = __test_api_key

command = """
너는 최고의 자연어 데이터 전문가야.
특히 키워드와 키워드의 유사성을 검사하는데 특화되어있지.
아래의 mapping_data에서 어색하게 연결된 키워드를 제거해서 Json의 형태로 반환해.
이때, Json 외에는 다른 어떠한 말도 더 하면 안돼.
"""


def make_prompt(data: dict):
    prompt: str = command + "\n\n\n" + json.dumps(data, ensure_ascii=False)
    return prompt


class OpenAIService:

    def __init__(self):
        pass

    def generate_chat_reply(self, data: str, depth=1):
        content = make_prompt(data)
        messages = [{"role": "user", "content": content}]

        completion = openai.ChatCompletion.create(
            # model="gpt-3.5-turbo-0613",
            model="gpt-4-0613",
            messages=messages
        )
        chat_response_str: str = completion.choices[0].message.content
        try:
            chat_response = json.loads(chat_response_str)
        except:
            # 데이터를 parsing하지 못하는 경우 3번까지 재귀호출
            if depth == 3:
                return None
            chat_response = self.generate_chat_reply(data, depth+1) 
        return chat_response
