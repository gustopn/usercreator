import random

outputstring = ''
counter = 9
while counter > 0:
    outputstring = outputstring + chr(random.randrange(97,122))
    counter = counter - 1

print outputstring
