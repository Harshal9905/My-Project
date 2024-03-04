import random
import json

import torch

from model import NeuralNet
from nltk_utils import bag_of_words, tokenize

from main import takecommand

device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')

with open('intents.json', 'r') as json_data:
    intents = json.load(json_data)

FILE = "data.pth"
data = torch.load(FILE)

input_size = data["input_size"]
hidden_size = data["hidden_size"]
output_size = data["output_size"]
all_words = data['all_words']
tags = data['tags']
model_state = data["model_state"]

model = NeuralNet(input_size, hidden_size, output_size).to(device)
model.load_state_dict(model_state)
model.eval()

bot_name = "KBP"


def get_response(msg):
    sentence = tokenize(msg)
    # print(sentence, "tokenize")
    X = bag_of_words(sentence, all_words)
    # print(X, "bow")
    X = X.reshape(1, X.shape[0])
    # print(X, "reshape")
    X = torch.from_numpy(X)
    # print(X, "torh")

    output = model(X)
    # print(output)
    _, predicted = torch.max(output, dim=1)
    # print(predicted.item())
    tag = tags[predicted.item()]

    probs = torch.softmax(output, dim=1)
    prob = probs[0][predicted.item()]
    # print(prob.item())
    if prob.item() > 0.75:
        for intent in intents['intents']:
            if tag == intent["tag"]:
                return random.choice(intent['responses'])

    return "I do not understand..."


if __name__ == "__main__":
    print("Let's chat! (type 'quit' to exit)")
    while True:
        # print("Press 1: voice\nPress 2:type")
        # ch = int(input())
        # if(ch==1):
        #     # voice recognition
        #     sentence = takecommand().lower()
        #
        #
        #     pass
        # elif(ch==2):
            # sentence = "do you use credit cards?"
        sentence = input("You: ")
        if sentence == "quit":
            break

        resp = get_response(sentence)
        print(resp)