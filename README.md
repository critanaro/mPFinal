# MousePass DEV EDITION
Creating 'Protecting your information one click at a time' one development at a time.

## How to run the app

- Clone repository onto desktop or some known location.
- Open Terminal and CD to the file location. 
- Install tensorflow using a virtual environment (venv) for Python 2.7 following the instructions here: https://www.tensorflow.org/install/pip?lang=python2
- There should now be a newly created folder named "venv" within the file location.
- Run the java file within the "src" folder using eclipse (or ide of your choice).
### NOTE
- Don't push changes directly to this repository after installing venv: the file is too big to be accepted to Github. Only push the training data.
  - So you may copy the repository destination into a different file on your computer, make all the modifications, copy the training data back into the repository destination, and push the file with only the training data added.

## Ideology

- Right now, our data doesn't account for direction of mouse movement, so our training is not homogenous. I.E: The program may mess up depending on what side (right, left, or center of the screen) you move your mouse. Which is why whenever we were demoing sometimes we could get it to let us in and other times we couldn't.

## Heuristic Guideline

- Create 3 separate training data banks. 
  - Left, Center, Right. (These will be called based on the correct answer of the question).
- Recode the applet for each bank to only have a button on one side (just remove the other buttons). 
  - Have trainees run several tests for each training bank (you decide how to design these tests). 
 
