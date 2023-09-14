import subprocess


def is_pip_installed():
    try:
        subprocess.check_call(["pip", "--version"])
        return True
    except subprocess.CalledProcessError:
        return False


def install_pip():
    subprocess.check_call(["curl", "https://bootstrap.pypa.io/get-pip.py", "-o", "get-pip.py"])
    subprocess.check_call(["python", "get-pip.py"])


def install_dependencies():
    print("install start")
    if not is_pip_installed():
        install_pip()

    subprocess.check_call(["pip", "install", "-r", "requirements.txt"])
    print("install done")


if __name__ == "__main__":
    install_dependencies()
