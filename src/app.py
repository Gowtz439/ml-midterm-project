from fastapi import FastAPI
import pickle
import numpy as np
from pydantic import BaseModel

with open("model/model.pkl", "rb") as f:
    model = pickle.load(f)

app = FastAPI(title="House Price Prediction API")

class HouseData(BaseModel):
    area: float
    bedrooms: int
    age: int

@app.get("/")
def root():
    return {"message": "House Price API Running"}

@app.post("/predict")
def predict_house(data: HouseData):
    features = np.array([[data.area, data.bedrooms, data.age]])
    pred = model.predict(features)[0]
    return {"predicted_price": float(pred)}
