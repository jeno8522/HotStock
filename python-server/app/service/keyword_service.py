from krwordrank.word import KRWordRank
from mecab import MeCab


min_count = 2   # 단어의 최소 출현 빈도수 (그래프 생성 시)
max_length = 10  # 단어의 최대 길이
beta = 0.90    # PageRank의 decaying factor beta
max_iter = 20
extract_length = 5  # 우선순위로 정렬 후, 추출할 키워드의 최대 개수

class KeywordService:

    def __init__(self):
        self.mecab = MeCab()

    # FIXME : 영어만 들어오는 경우 아예 추출하지 못함. 추출하지 못하는 경우 에러가 발생하니 처리가 필요함
    def get_keyword(self, id, title, content):
        text = title + " " + content
        res = dict()

        result_text = ' '.join(self.mecab.nouns(text))
        wordrank_extractor = KRWordRank(
            min_count=min_count, max_length=max_length)

        # FIXME : 추출하지 못하는 경우 에러가 발생하니 처리가 필요함. 현재는 그냥 빈 dict를 반환하도록 설정함.
        try:
            keywords, rank, graph = wordrank_extractor.extract(
                [result_text], beta, max_iter)
        except:
            return res

        for word, r in sorted(keywords.items(), key=lambda x: x[1], reverse=True)[:extract_length]:
            # TODO: 가중치는 보완 필요
            # res[word] = r
            res[word] = [id]

        return res


    # :여러개의 text를 list로 받아 키워드를 추출하는 함수
    # :list -> dict
    def get_keywords(self, data: list):
        res = dict()

        # 각 텍스트의 키워드들을 합쳐준다.
        for id, title, content in data:
            keywords: dict = self.get_keyword(id, title, content)
            for key, value in keywords.items():
                if key in res:
                    res[key] += value
                else:
                    res[key] = value

        return res
