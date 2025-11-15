import pickle

# Load the saved model
with open("model/model.pkl", "rb") as f:
    model = pickle.load(f)

print("🏠 House Price Prediction (Terminal Mode)")
print("-----------------------------------------")

try:
    # Take inputs from user
    area = float(input("Enter house area (sq ft): "))
    bedrooms = int(input("Enter number of bedrooms: "))
    age = int(input("Enter house age (years): "))

    features = [[area, bedrooms, age]]

    # Predict
    predicted_price = model.predict(features)[0]

    print(f"\n💰 Predicted House Price: ₹ {round(predicted_price, 2)}")

except Exception as e:
    print(f"❌ Error: {e}")
