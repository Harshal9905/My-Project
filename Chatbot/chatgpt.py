from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer

# Create a new chatbot
chatbot = ChatBot('CollegeBot')

# Create a new trainer for the chatbot
trainer = ChatterBotCorpusTrainer(chatbot)

# Train the chatbot on English language data
trainer.train('chatterbot.corpus.english')

# Define a function to interact with the chatbot
def chat_with_bot():
    print("CollegeBot: Hello! I'm CollegeBot. You can ask me questions about the college.")
    while True:
        user_input = input("You: ")
        if user_input.lower() == 'exit':
            print("CollegeBot: Goodbye!")
            break
        response = chatbot.get_response(user_input)
        print("CollegeBot:", response)

# Start the conversation
chat_with_bot()
