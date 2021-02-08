import smtplib
from email.mime.multipart import MIMEMultipart
import firebase_admin
import time
from firebase_admin import credentials
from email.mime.text import MIMEText
from firebase_admin import firestore


cred=credentials.Certificate("tcs-wealth-firebase-adminsdk-b7woy-001ab8a94f.json")
firebase_admin.initialize_app(cred)
db=firestore.client()
doc=db.collection('users').get()
sender_address = 'muvvaladeepthi814@gmail.com'
sender_pass = 'ohbisdbrzqawfxdd'
message = MIMEMultipart()
message['From'] = sender_address
message['Subject'] = 'Summary'





def Data(id):
    UserData=db.collection(u'users').document("1").collection(u'Parameters').document(u'Parameters').get().to_dict()
    data=UserData["Data"]
    daystart = data[0]

    dayend = data[len(data) - 1]

    middle1 = data[int(len(data) / 4)]



    Message = "\n" + "Day starting report \n" + "\n " + str(daystart) + "\n\n\n  report 2 \n " + "\n" + str(
        middle1) + "\n\n\n " + "\n\nEvening report \n" + "\n" + str(dayend)


    Mail(id,Message)



    time.sleep(3)



def Mail(receiver_address,Message):

    mail_content =Message;
    message.attach(MIMEText(mail_content, 'plain'))
    session = smtplib.SMTP('smtp.gmail.com', 587)
    session.starttls()
    session.login(sender_address, sender_pass)
    text = message.as_string()

    session.sendmail(sender_address, receiver_address, text)
    session.quit()




for i in doc:
    print(i)
    each=i.to_dict()
    id=each["email"]
    Data(id);

