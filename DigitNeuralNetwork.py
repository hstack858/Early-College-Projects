from sklearn.datasets import fetch_openml

# get_mnist:  takes no arguments, connects over the internet to retrieve the
# MNIST digits data in the form of a matrix where rows are inputs, and a vector
# that contains the true label of the digit (0-9)
def get_mnist():
    examples, labels = fetch_openml('mnist_784', version=1, return_X_y=True)
    return examples, labels

X, y = get_mnist()

import numpy as np
import matplotlib.pyplot as plt

MNIST_WIDTH = 28

# show_mnist_index:  code to graphically display example i from matrix examples
# (which comes from get_mnist or similar)
def show_mnist_index(examples, i):
    # The colon operator selects "all" as the index, grabbing a whole row or column
    data_array = np.array(examples[i,:])
    # reshape takes the vector-like data and turns it into 2D, restoring the original shape
    image = np.reshape(data_array, (MNIST_WIDTH, MNIST_WIDTH))
    # Draw the image
    plt.imshow(image)
    # But matplotlib needs to be told to create the window or image
    plt.show()
    
  from sklearn.neural_network import MLPClassifier

# trained_network:  returns a neural network object ("clf" for "classifier")
# that can learn via stochastic gradient descent then make predictions about new data
def trained_network(examples,labels):
    clf = MLPClassifier(solver='sgd',learning_rate='constant',learning_rate_init=0.001,max_iter=400,
            shuffle=True)
    clf.fit(examples,labels)
    return clf
    
# predict_mnist:  just a wrapper for a neural network's predict() function
# clf is the neural network returned earlier, examples is the example matrix from
# downloading the MNIST data, and i is an index to the ith example
def predict_mnist(clf, examples, i):
    return clf.predict([examples[i,:]])

# your training and printing of classifications here
A = trained_network(X, y)
