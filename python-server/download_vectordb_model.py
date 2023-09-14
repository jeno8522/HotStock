import gdown
import os

def download_vectordb_model():
    google_path = 'https://drive.google.com/uc?id='
    file_id = '1t2P9RSodbLWQBGbla6zzYXYH054pQVyR'
    output_name = 'vectordb_model/ko.bin'
    
    # Ensure the output directory exists
    output_directory = os.path.dirname(output_name)
    if not os.path.exists(output_directory):
        os.makedirs(output_directory)
    
    gdown.download(google_path + file_id, output_name, quiet=False)
    
if __name__ == "__main__":
    download_vectordb_model()
