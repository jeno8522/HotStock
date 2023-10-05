from app.service.keyword_service import KeywordService
from app.service.theme_service import ThemeService
import csv

keyword_service = KeywordService()
theme_service = ThemeService()

def study(file_path:str):
    with open(file_path, mode='r', encoding='utf-8') as csv_file:
        csv_reader = csv.DictReader(csv_file, delimiter='|')

        for row in csv_reader:
            title = row["title"]
            article = row["article"]
            keyword_service.get_keyword(1, title, article)

def save():
    theme_service.save_updated_model()