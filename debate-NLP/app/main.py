from fastapi import FastAPI
from pydantic import BaseModel
from sentence_transformers import SentenceTransformer
app = FastAPI(
    title="Debate Platform NLP Service",
    description="NLP microservice for argument classification",
    version="0.1.0"
)
model = SentenceTransformer("all-MiniLM-L6-v2")


class HealthResponse(BaseModel):
    status: str
    version: str


@app.get("/health", response_model=HealthResponse)
async def health():
    return {
        "status": "ok",
        "version": "0.1.0"
    }


class ClassifyRequest(BaseModel):
    text: str


class ClassifyResponse(BaseModel):
    argument_type: str
    confidence: float

class SimilarityRequest(BaseModel):
    text1: str
    text2: str


class SimilarityResponse(BaseModel):
    similarity: float

@app.post("/classify", response_model=ClassifyResponse)
async def classify(request: ClassifyRequest):

    text = request.text.lower()

    if "because" in text or "therefore" in text:
        argument_type = "REASONING"
        confidence = 0.85

    elif "i think" in text or "i believe" in text:
        argument_type = "OPINION"
        confidence = 0.80

    else:
        argument_type = "CLAIM"
        confidence = 0.70

    return {
        "argument_type": argument_type,
        "confidence": confidence
    }
@app.post("/similarity", response_model=SimilarityResponse)
async def similarity(request: SimilarityRequest):

    embeddings = model.encode(
        [request.text1, request.text2]
    )

    similarity_score = model.similarity(
        embeddings[0],
        embeddings[1]
    )

    return {
        "similarity": float(similarity_score)
    }