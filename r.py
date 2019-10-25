#!/usr/bin/python
import sys
word2count={}

for line in sys.stdin:
    line = line.strip()
    word,count = line.split("\t",1)

    try:
        count = int(count)
    except:
        continue
    try:
        word2count[word]+=count
    except:
        word2count[word]=count

for word in word2count.keys():
    print(word+"\t"+str(word2count[word]))
