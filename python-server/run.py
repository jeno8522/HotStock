import os


if not os.path.exists('vectordb_model/ko.bin'):
    from download_vectordb_model import download_vectordb_model
    download_vectordb_model()
    
from app import app


if __name__ == '__main__':
    app.run(debug=True)
