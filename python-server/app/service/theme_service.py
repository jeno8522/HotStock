from app.service.openai_service import OpenAIService
from gensim.models import FastText
import heapq
import re

snapshot = ["2차전지", "2차전지(LFP/리튬인산철)", "2차전지(생산)", "2차전지(소재/부품)", "2차전지(장비)", "2차전지(전고체)", "3D 낸드(NAND)", "3D 프린터", "4대강 복원", "4차산업 수혜주", "5G(5세대 이동통신)", "AI 챗봇(챗GPT 등)", "CCTV＆DVR", "DMZ 평화공원", "GTX(수도권 광역급행철도)", "K-뉴딜지수", "K-뉴딜지수(2차전지)", "K-뉴딜지수(게임)", "K-뉴딜지수(바이오)", "K-뉴딜지수(인터넷)", "LCD BLU제조", "LCD 부품/소재", "LCD장비", "LED", "LED장비", "LNG(액화천연가스)", "LPG(액화석유가스)", "MLCC(적층세라믹콘덴서)", "mRNA(메신저 리보핵산)", "MVNO(가상이동통신망사업자)", "NFT(대체불가토큰)", "NI(네트워크통합)", "OLED(유기 발광 다이오드)", "PCB(FPCB 등)", "RFID(NFC 등)", "SI(시스템통합)", "SNS(소셜네트워크서비스)", "SSD", "STO(증권형 토큰 발행)", "U-Healthcare(원격진료)", "UAM(도심항공모빌리티)", "가상현실(VR)", "가상화폐(비트코인 등)", "강관업체(Steel pipe)", "갤럭시 부품주", "건강기능식품", "건설 대표주", "건설 중소형", "건설기계", "게임", "겨울", "고령화 사회(노인복지)", "골판지 제조", "골프", "공기청정기", "공작기계", "광고", "교육/온라인 교육", "구제역/광우병 수혜", "구충제(펜벤다졸, 이버멕틴 등)", "국내 상장 중국기업", "그래핀", "기업인수목적회사(SPAC)", "남-북-러 가스관사업", "남북경협", "네옴시티", "농업", "니켈", "도시가스", "두나무(Dunamu)", "드론(Drone)", "렌터카", "로봇(산업용/협동로봇 등)", "리모델링/인테리어", "리비안(RIVIAN)", "리츠(REITs)", "리튬", "마리화나(대마)", "마스크", "마이데이터", "마이크로 LED", "마이크로바이옴", "마켓컬리(kurly)", "맥신(MXene)", "메르스 코로나 바이러스", "메타버스(Metaverse)", "면세점", "면역항암제", "모더나(MODERNA)", "모듈러주택", "모바일게임(스마트폰)", "모바일솔루션(스마트폰)", "모바일콘텐츠(스마트폰/태블릿PC)", "무선충전기술", "미디어(방송/신문)", "미용기기", "바이오시밀러(복제 바이오의약품)", "바이오인식(생체인식)", "반도체 대표주(생산)", "반도체 장비", "반도체 재료/부품", "밥솥", "방위산업/전쟁 및 테러", "백신/진단시약/방역(신종플루, AI 등)", "백신여권", "백화점", "보안주(물리)", "보안주(정보)", "보톡스(보툴리눔톡신)", "북한 광물자원개발", "블록체인", "비료", "비만치료제", "비철금속", "사료", "사물인터넷", "삼성페이", "생명보험", "석유화학", "선박평형수 처리장치", "셰일가스(Shale Gas)", "소매유통", "소모성자재구매대행(MRO)", "손해보험", "수산", "수소차(연료전지/부품/충전소 등)", "슈퍼박테리아", "스마트그리드(지능형전력망)", "스마트카(SMART CAR)", "스마트팩토리(스마트공장)", "스마트폰", "스마트홈(홈네트워크)", "스포츠행사 수혜(올림픽, 월드컵 등)", "시멘트/레미콘", "시스템반도체", "아스콘(아스팔트 콘크리트)", "아이폰", "아프리카 돼지열병(ASF)", "애플페이", "야놀자(Yanolja)", "양자암호", "엔젤산업", "엔터테인먼트", "엠폭스(원숭이두창)", "여름", "여행", "영상콘텐츠", "영화", "온실가스(탄소배출권)", "요소수", "우주항공산업", "우크라이나 재건", "원자력발전", "원자력발전소 해체", "웹툰", "유전자 치료제/분석", "육계", "윤활유", "은행", "음성인식", "음식료업종", "음원/음반", "의료기기", "인터넷 대표주", "인터넷은행", "일자리(취업)", "자동차 대표주", "자동차부품", "자원개발", "자율주행차", "자전거", "재난/안전(지진 등)", "재택근무/스마트워크", "전기자전거", "전기차", "전기차(충전소/충전기)", "전력설비", "전력저장장치(ESS)", "전선", "전자결제(전자화폐)", "전자파", "정유", "제4이동통신", "제대혈", "제습기", "제약업체", "제지", "조림사업", "조선", "조선기자재", "종합 물류", "종합상사", "주류업(주정, 에탄올 등)", "줄기세포", "증강현실(AR)", "증권", "지능형로봇/인공지능(AI)", "지주사", "차량용블랙박스", "창투사", "철강 주요종목", "철강 중소형", "철도", "초전도체", "출산장려정책", "치매", "치아 치료(임플란트 등)", "카메라모듈/부품", "카지노", "카카오뱅크(kakao BANK)", "캐릭터상품", "케이블TV SO/MSO", "코로나19(나파모스타트)", "코로나19(덱사메타손)", "코로나19(렘데시비르)", "코로나19(스푸트니크V)", "코로나19(음압병실/음압구급차)", "코로나19(진단/치료제/백신 개발 등)", "코로나19(진단키트)", "코로나19(치료제/백신 개발 등)", "코로나19(카모스타트)", "코로나19(혈장치료/혈장치료제)", "콜드체인(저온 유통)", "쿠팡(coupang)", "크래프톤 관련주", "클라우드 컴퓨팅", "키오스크(KIOSK)", "타이어", "탄소나노튜브(CNT)", "탈모 치료", "태블릿PC", "태양광에너지", "태풍 및 장마", "터치패널(스마트폰/태블릿PC 등)", "테마파크", "토스(toss)", "통신", "통신장비", "패션/의류", "페라이트", "페인트", "편의점", "폐기물처리", "폐배터리", "폴더블폰", "풍력에너지", "플렉서블 디스플레이", "피팅(관이음쇠)/밸브", "핀테크(FinTech)", "항공/저가 항공사(LCC)", "항공기부품", "해운", "해저터널(지하화/지하도로 등)", "핵융합에너지", "홈쇼핑", "화이자(PFIZER)", "화장품", "화폐/금융자동화기기(디지털화폐 등)", "화학섬유", "환율하락 수혜", "황사/미세먼지", "휴대폰부품", "희귀금속(희토류 등)", "日 수출 규제(국산화 등)", "日제품 불매운동(수혜)"]
openai_service = OpenAIService()

#기존의 bin은 full파일이지만, 파이썬에서 새로 저장한 bin 파일은 full 파일이 아님. .npy파일이 필수적
try :
    ko_model = FastText.load_fasttext_format('vectordb_model/ko.bin')
except :
    ko_model = FastText.load('vectordb_model/ko.bin')

#TODO : 테마 아이디를 가지고 있어야 나중에 저장하기 쉬움.
def map_keywords_themes(keywords: list):
    themes = snapshot

    # 키워드와 테마의 유사도를 최소힙에 저장
    keyword_dict = dict()
    for keyword in keywords:
        for theme in themes:
            if keyword_dict.get(keyword) == None:
                keyword_dict[keyword] = []
                
            theme_subjects = re.split(r" |\(",theme.replace(r" \등\)|\)",""))
            
            similarity = 0
            for subj in theme_subjects:
                similarity = max(similarity, ko_model.wv.similarity(keyword, subj))
            
            heapq.heappush(
                keyword_dict[keyword], (-similarity, theme))

    # 유사도가 53% 이상인 경우에만 저장
    mapping_data = dict()
    for keyword in keywords:
        now = keyword_dict[keyword]
        temp_list = []
        while now:
            data = heapq.heappop(keyword_dict[keyword])
            if (data[0] > -0.53) :
                if not temp_list : 
                    temp_list.append(data[1])
                break
            temp_list.append(data[1])
            if len(temp_list) >= 8:
                break
        mapping_data[keyword] = temp_list

    return mapping_data

class ThemeService:

    def __init__(self):
        pass

    def get_theme_infos(self):
        # TODO : 수정 예정, 실시간으로 가져오는 코드로 변경 후, snapshot을 레디스에 저장하고 처리하도록 처리해야함.
        return snapshot

    def get_themes_of_keywords(self, keywords: list, mode = 'non-gpt'):
        mapping_data: dict = map_keywords_themes(keywords) # keyword:str -> [theme:str]
        if mode == 'gpt' :
            chat_response = openai_service.generate_chat_reply(mapping_data)
        elif mode == 'non-gpt' :
            chat_response = mapping_data
        return chat_response
