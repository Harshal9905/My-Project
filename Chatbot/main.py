import os
import subprocess
import speech_recognition as sr
import win32com.client
import webbrowser
import datetime

# make jarvis to speak
def say(s):
    speaker = win32com.client.Dispatch("SAPI.Spvoice")
    speaker.speak(s)

# jarvis take command from user
def takecommand():
    r = sr.Recognizer()

    with sr.Microphone() as source:
        r.pause_threshold = 1
        audio = r.listen(source)

        try:
            print("wait for few moments...")
            query = r.recognize_google(audio, language='en-in')
            print(f"User said: {query}\n")
            say(query)

        except Exception as e:
            print(e)
            say("please tell me again")
            query = "none"

        return query



if __name__ == '__main__':
    print('PyCharm')
    say("Hello I am Harshal From K B P A I")
    while True:
        print("Listening...")

        query = takecommand().lower()

        # for website
        sites =[["youtube", "www.youtube.com"],["map", "https://www.google.com/maps/search/kbp+college+satara/@17.6875464,74.0103383,17z/data=!3m1!4b1?entry=ttu"],["course offer", "https://www.kbpcoes.edu.in/course_offered.php"],["college Website","https://kbpcoes.edu.in"],["about institute","https://www.kbpcoes.edu.in/about_college.php"]]
        for site in sites:
            if f"open {site[0]}".lower() in query.lower():
                say(f"opening {site[0]} Sir Harshal")
                webbrowser.open(site[1])

        # for time
        if "the time".lower() in query:
            strfTime = datetime.datetime.now().strftime("%H:%M:%S")
            say(f"time is {strfTime}")
            print(strfTime)

        # to stop the jarvis
        if "stop".lower() in query:
            say("bye bye Harshal")
            break

        if "head of department".lower() in query:
            say("Professor Ganesh Dangat Sir")
            print("Prof. Ganesh Dangat")

