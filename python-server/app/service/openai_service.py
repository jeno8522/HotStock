import json
import openai

# FIXME : 실제 서비스에서는 사용할 수 없는 API 키 (테스트 이후 삭제되는 키)
__test_api_key = "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"

openai.api_key = __test_api_key

command = """
너는 최고의 자연어 데이터 전문가야.
특히 키워드와 키워드의 유사성을 검사하는데 특화되어있지.
아래의 mapping_data에서 어색하게 연결된 부분을 없애서 Json의 형태로 반환해.
이때, Json 외에는 다른 어떠한 말도 더 하면 안돼.
"""


def make_prompt(data: dict):
    prompt: str = command + "\n\n\n" + json.dumps(data, ensure_ascii=False)
    return prompt


class OpenAIService:

    def __init__(self):
        pass

    def generate_chat_reply(self, data: str):
        content = make_prompt(data)
        messages = [{"role": "user", "content": content}]

        completion = openai.ChatCompletion.create(
            # model="gpt-3.5-turbo-16k-0613",
            model="gpt-4-0613",
            messages=messages
        )
        chat_response: str = completion.choices[0].message.content

        return chat_response
