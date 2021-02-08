import firebase_admin
import time
import random
from time import strftime
from firebase_admin import credentials
from firebase_admin import firestore
from google.cloud.firestore import ArrayUnion


pas=credentials.Certificate("tcs-wealth-firebase-adminsdk-b7woy-001ab8a94f.json")
firebase_admin.initialize_app(pas)
db=firestore.client()


while True:
    doc = db.collection('users').get()
    index=0

    for i in doc:

                    doc_ref = db.collection(u'users').document(str(index)).collection(u'Parameters').document(u'Parameters')
                    bp=random.randint(90,123)
                    bodyTempatature=random.randint(92,102)
                    respiration=random.randint(12,20)
                    glucose=random.randint(132,215)
                    heart_Rate=random.randint(60,75)
                    oxygen_Saturation=random.randint(95,102)
                    electroCardiogram=random.randint(57,115)
                    index+=1

                    doc_ref.set({
                      'Data': ArrayUnion([
                                {
                                                    u"bodyTemparature": u""+str(bodyTempatature),
                                                     u"glucose": u"" + str(glucose),

                                                    u"respiration":u""+str(respiration),
                                                     u"electroCardiogram": u"" + str(electroCardiogram),

                                                    u"heartRate":u""+str(heart_Rate),
                                                     u"bp": u"" + str(bp),
                                                    u"oxygenSaturation":u""+str(oxygen_Saturation),


                                          }

                                ]
                                )
                                },merge=True,
                                )

    time.sleep(30)
                    

