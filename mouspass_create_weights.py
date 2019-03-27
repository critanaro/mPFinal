import os
import csv
import pydot
import graphviz
# TensorFlow and tf.keras
import tensorflow as tf
from tensorflow import keras

from keras.utils.vis_utils import plot_model
#model selection
# model = 'middle'
# if model == 'left':
#     filename = 'lefttotal.csv'
#     checkname = 'training_left/cp.ckpt'
#     testname = 'testdataleft.csv'
# if model == 'middle':
#     filename = 'middletotal.csv'
#     checkname = 'training_middle/cp.ckpt'
#     testname = 'testdatamiddle.csv'
# if model == 'right':
#     filename = 'righttotal.csv'
#     checkname = 'training_right/cp.ckpt'
#     testname = 'testdataright.csv'

filename = 'total.csv'
checkname = 'training/cp.ckpt'
testname = 'testdata.csv'

lengthofoneq = 400#10,000
with open(filename) as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    masterlist = []
    list = []
    counter = 0
    for row in readCSV:
        if counter == 0:
            pass
        elif counter % lengthofoneq == 0:
            masterlist.append(list)
            #print (len(list), list,  masterlist)
            list = []
        list.append((int(row[0]), int(row[1])))
        counter += 1
with open(testname) as csvfile:
    readCSV = csv.reader(csvfile, delimiter=',')
    masterlisttest = []
    list = []
    counter = 0
    for row in readCSV:
        if counter == 0:
            pass
        elif counter % (lengthofoneq) == 0:
            masterlisttest.append(list)
            # print("test",len(list), list, masterlisttest)
            list = []
        list.append((int(row[0]), int(row[1])))
        counter += 1

# Helper libraries
import numpy as np
import matplotlib.pyplot as plt

print(tf.__version__)

#fashion_mnist = keras.datasets.fashion_mnist
#(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

list = []
for i in range(400):
    #print "i",i,"list",list
    if i <= 34:
        list.append(0)
    elif i <= 69:
        list.append(1)
    elif i <= 104:
        list.append(2)
    elif i <= 139:
        list.append(3)
    elif i <= 174:
        list.append(4)
    elif i <= 208:
        list.append(5)
#print list

# listtest = []
# for i in range(400):
#     #print "i",i,"list",list
#     if i <= 5:
#         listtest.append(0)
#     elif i <= 11:
#         listtest.append(1)
#     elif i <= 17:
#         listtest.append(2)
#     elif i <= 23:
#         listtest.append(3)
#     elif i <= 29:
#         listtest.append(4)
#     elif i <= 35:
#         listtest.append(5)
# print listtest

#Import the DataSet
#train_images = masterlist
train_labels = np.array(list)
train_images = masterlist
#train_labels = np.array([1,1,0,0])
test_images = masterlisttest
#test_images = masterlist
#test_labels = train_labels
test_labels = np.array([0,1,0,1,0,1,0,2,3,2,3,2,3,2,4,5,4,5,4,5›])

#print (train_images)
#print (test_images)
# train_images = [[(0,0),(0,0),(1,2),(3,4),(6,7),(9,11),(0,0)],[(0,0),(6,5),(3,4),(7,6),(10,4),(9,13),(0,0)]]
train_images = np.asarray(train_images)
# test_images = [[(0,0),(0,0),(1,2),(3,4),(6,7),(9,11),(0,0)],[(0,1),(0,0),(1,2),(3,5),(6,7),(10,11),(0,0)]]
test_images = np.asarray(test_images)
train_images = train_images / 1500.0
test_images = test_images / 1500.0
# train_labels = np.array([1, 0])
# test_labels = np.array([1, 1])
#train_labels = np.asarray(train_labels)
class_names = ['True','False']
#print (train_images)
#print (train_images.shape)
#print (len(train_labels))
#print (train_labels)
#class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
#test
#train_images = train_images / 255.0

#test_images = test_images / 255.0




#Build the model



model = tf.keras.models.Sequential([
	keras.layers.Flatten(input_shape=(lengthofoneq, 2)),
	keras.layers.Dense(1280, activation=tf.nn.relu),
	keras.layers.Dense(6, activation=tf.nn.softmax)
	])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

"""
model = create_model()
model.summary()
"""


#create checkpoints
checkpoint_path = checkname
checkpoint_dir = os.path.dirname(checkpoint_path)

# Create checkpoint callback
cp_callback = tf.keras.callbacks.ModelCheckpoint(checkpoint_path,
                                                 save_weights_only=True,
                                                 verbose=1)

#model.fit(train_images, train_labels, epochs=10)
model.fit(train_images, train_labels,  epochs = 10,
           validation_data = (test_images,test_labels),
           callbacks = [cp_callback], verbose =0)  # pass callback to training

#tests while untrained
loss, acc = model.evaluate(test_images, test_labels)
print("trained model, accuracy: {:5.2f}%".format(100*acc))

"""
model1.load_weights(checkpoint_path)
loss,acc = model1.evaluate(test_images, test_labels)
print("Restored model, accuracy: {:5.2f}%".format(100*acc))
"""

#testing

test_loss, test_acc = model.evaluate(test_images, test_labels)

print('Test accuracy:', test_acc)


img = test_images[1]
img = (np.expand_dims(img, 0))
predictions_single = model.predict(img)

print(predictions_single)
x = np.argmax(predictions_single[0])
print(x)

plot_model(model, to_file='model_plot.png', show_shapes=True, show_layer_names=True);
