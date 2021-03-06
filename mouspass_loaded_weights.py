import csv
import sys
import tensorflow as tf
import numpy as np
from tensorflow import keras

# with open("did_it_work3", "w") as f:
#     f.write("yeehaw")
#     f.write(sys.version)
lengthofoneq = 400#10,000
lengthtotal = 2000#50,000
### IMPORT FROM CSV
with open('inputdata.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    masterlist = []
    list = []
    counter = 0
    for row in readCSV:
        #print (counter)
        if counter == 0:
            list.append((int(row[0]), int(row[1])))
            counter+=1
            pass
        elif counter % lengthofoneq == 0:
            masterlist.append(list)
            #print(len(list), list, masterlist)
            list = []
        # elif counter == 1999:
        #     masterlist.append(list)
        #     print(len(list), list, masterlist)
        #     list = []
        list.append((int(row[0]), int(row[1])))
        counter += 1


#print(masterlist)
saved_model_path = "training/cp.ckpt"
model = keras.Sequential([
    keras.layers.Flatten(input_shape=(lengthofoneq, 2)),
    keras.layers.Dense(128, activation=tf.nn.relu),
    keras.layers.Dense(2, activation=tf.nn.softmax)
])

model.compile(optimizer='adam',
               loss=tf.keras.losses.sparse_categorical_crossentropy,
               metrics=['accuracy'])

model.load_weights(saved_model_path)
#loss,acc = model.evaluate(test_images, test_labels)
#print("Restored model, accuracy: {:5.2f}%".format(100*acc))

f = open('printfile.txt', 'w')
counter2 = 0
for i in range(0,3):
    #print (i)
    masterlistq = masterlist[i]
    img = (np.expand_dims(masterlistq, 0))
    predictions_single = model.predict(img)
    #print(predictions_single)
    x = np.argmax(predictions_single[0])
    #print(x)
    if (x == 0):
        print "left confident"
    if (x == 1):
        print "left intruder"
    if (x == 2):
        print "middle confident"
    if (x == 3):
        print "middle intruder"
    if (x == 4):
        print "right confident"
    if (x == 5):
        print "right intruder"
    if (x % 2) == 0:
        counter2 += 1
       # print "good kid mad city"
if counter2 >= 2:
    f.write("true")
    print ("true")
else:
    f.write("false")
    print ("false")
f.close()



