from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC



chrome_options = Options()
chrome_options.add_argument("headless")
chrome_options.add_argument("no-sandbox")
chrome_options.add_argument("window-size=1920x1080")
chrome_options.add_argument("disable-gpu")
chrome_options.add_argument("lang=ko_KR")
chrome_options.add_argument("disable-dev-shm-usage")
service = Service(ChromeDriverManager().install())

class NewsService:

    def __init__(self):
        pass

    def get_news(self, media_company_id, article_id) -> list:
        driver = webdriver.Chrome(service=service, options=chrome_options)
        
        media_company_id = int(media_company_id)
        article_id = int(article_id)

        # 크롬드라이버 실행
        
        # 뉴스 페이지로 이동
        link = f"https://n.news.naver.com/article/{media_company_id:03}/{article_id:010}"
        driver.get(link)

        summaryContent = ""

        try:
            while True:
                try:
                    # 요약 봇 버튼을 찾아 클릭
                    summaryButton = driver.find_element(By.CSS_SELECTOR, "a.media_end_head_autosummary_button")
                    summaryButton.click()

                    try:
                        # 요약된 내용을 가져오기 (선택자를 요약 봇 레이어의 클래스 이름으로 지정)
                        # 요약 내용이 로딩될 때까지 대기
                        timeout = 3  # 최대 3초 대기 (필요에 따라 조정)
                        wait = WebDriverWait(driver, timeout)
                        summaryContentElement = wait.until(EC.visibility_of_element_located(
                            (By.CSS_SELECTOR, "div.media_end_head_autosummary_layer_body ._contents_body._SUMMARY_CONTENT_BODY")))
                        summaryContent = summaryContentElement.text

                        break
                    except Exception as e:
                        # TimeoutException 발생 시 알림 또는 로깅
                        print("TimeoutException 발생: 버튼 또는 요소가 나타나지 않음.")

                except Exception as e:
                    print("요약 봇 버튼을 찾을 수 없습니다.")
                    break

            # WebDriver를 사용하여 웹 페이지 크롤링을 계속 진행하세요
            # 필요한 데이터를 추출하세요 (제목, 본문, 날짜 등)
            
            # 아래에 필요한 데이터 추출 및 처리 코드를 작성하세요

        except Exception as e:
            print("오류 발생:", e)

        # WebDriver 종료
        driver.quit()

        news_data = {
            "summaryContent": summaryContent,
        }
        return news_data

