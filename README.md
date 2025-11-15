# House Price Prediction ML Project

This project demonstrates a complete ML workflow:

- Data preprocessing
- Model training (Random Forest)
- Model saving
- API deployment using FastAPI
- Docker containerization

## How to run locally

### 1. Install dependencies

pip install -r requirements.txt

### 2. Train the model

python src/train.py

### 3. Run the API

uvicorn src.app:app --reload

## Docker Usage

### Build image

docker build -t house-ml .

### Run container

docker run -p 8000:8000 house-ml

Visit:http://localhost:8000/docs
