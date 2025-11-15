FROM python:3.10-slim

WORKDIR /app

COPY . .

RUN pip install fastapi uvicorn scikit-learn pandas numpy

EXPOSE 8000

CMD ["uvicorn", "src.app:app", "--host", "0.0.0.0", "--port", "8000"]
