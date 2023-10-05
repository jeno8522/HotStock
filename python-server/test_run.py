import os
from threading import Thread 
from app.test.study_data import study, save

if not os.path.exists('vectordb_model/ko.bin') or not os.path.exists('vectordb_model/ko.bin.wv.vectors_ngrams.npy'):
    from download_vectordb_model import download_vectordb_model
    download_vectordb_model()
    
from app import app


if __name__ == '__main__':
    print("process start")

    study_args = ["./app/test/ssafy_dataset_news_2022.csv"]
    th1 = Thread(target=study, args=(study_args))

    
    th1.start()

    th1.join()

    print("process end")
    save()
    print("save end")
    # app.run(debug=True, threaded=True)
    
