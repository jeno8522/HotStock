import gdown
import os

def download_from_google_drive(file_id:str, output_name:str):
    google_path = 'https://drive.google.com/uc?id='
    
    # Ensure the output directory exists
    output_directory = os.path.dirname(output_name)
    if not os.path.exists(output_directory):
        os.makedirs(output_directory)
    
    gdown.download(google_path + file_id, output_name, quiet=False)

def download_vectordb_model():
    download_from_google_drive('1C_C-0Mg0ADFaDT7j3umgEirtcRVKygKu', 'vectordb_model/ko.bin')
    download_from_google_drive('14aNlwJKuQV09f50cK9cpqgA3GUhTv4jc', 'vectordb_model/ko.bin.wv.vectors_ngrams.npy')
    
    
if __name__ == "__main__":
    download_vectordb_model()
    
