FROM python:3.9.0

WORKDIR /app

COPY requirements.txt ./requirements.txt

RUN python -m pip install --upgrade pip


RUN pip3 install flask
RUN apt-get update
RUN apt-get -y install libgl1-mesa-glx
RUN pip3 install -r requirements.txt

EXPOSE 5000

COPY . /app

CMD ["python3", "-m", "flask", "run", "--host=0.0.0.0"]