from random import shuffle
from random import randint
import random
import math
'''
Task:
Develop an application in python that has the following functionalities:
1. Randomly creates possible solutions for your assigned problem (Random Candidate
Solution Generator) (25p).
2. Check if a candidate to the solution is indeed a viable solution (25p).
3. Assigns a measure of quality (a positive value) to a candidate solution - where zero
marks a correct one and, as the candidate is more and more farther from the correct
solution the number grows (Fitness Function) (25p).
4. For a sample set (of size n) of random generated solutions, the mean and the standard
deviation of their quality measures is computed (25p).


A set of n questions are available in order to develop an aptitude test, question i being
rated with p points. It is required to develop questionnaires having the total number of
i
questions between u and v and summarizing between x and y points.


'''
def getRandom(x):
    shuffle(x)
    return x

def fitness(suma,x,y):
    if suma<x:
        return x-suma
    elif suma>y:
        return y+suma
    else:
        return 0

def main():
    n=30
    l=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
    u=input("input u(no of questions): ")
    v=input("input v(no of questions): ")
    x=input("input x(no of points): ")
    y=input("input y(no of points): ")
    
    fitnes=[]
    for i in range (0,n):
        questions = randint(u,v)#random between u and v
        elems = random.sample(l, questions)#take 'questions' number of elems from initial list
        suma = sum(elems)# take random numbers from list l
        fitnes.append(fitness(suma,x,y))
        if fitnes[i]==0:
            print('correct ',i+1, ' fitness', fitnes[i] ,'sum: ',suma, 'list: ',elems)
        else:
            print(i+1, ' fitness', fitnes[i] ,'sum: ',suma, 'list: ',elems)
    #mean
    m = float(sum(fitnes))/n
    print('media:',m)
    
    #calculate sum of (Fi-m)*(Fi-m) where Fi is i-th elem of fitness elems
    squareSum=0
    for i in range(0,n):
        squareSum+=(fitnes[i]-m)*(fitnes[i]-m)
    
    #standard deviation
    sd = math.sqrt(squareSum/n)
    print('standard deviation:',sd)

main()

