'''
Created on May 18, 2017

@author: Nicu
'''


class Temperature:
    def __init__(self, value):
        self.veryCold = 0
        self.cold = 0
        self.normal = 0
        self.warm = 0
        self.hot = 0
        self.fuzzy(value)

    def fuzzy(self, value):
        self.veryCold = self.trapezoidFuzzy(value, [-31, -30, -20, 5])
        self.cold = self.triangleFuzzy(value, [5, 0, 10])
        self.normal = self.trapezoidFuzzy(value, [5, 10, 15, 20])
        self.warm = self.triangleFuzzy(value, [15, 20, 25])
        self.hot = self.trapezoidFuzzy(value, [25, 30, 35, 36])

    def triangleFuzzy(self, value, l):
        res =  max(0, min((value - l[0])/(l[1] - l[0]), 1, (l[2] - value)/(l[2] - l[1])))
        print("temp:",res)
        return res

    def trapezoidFuzzy(self, value, l):
        res =  max(0, min((value-l[0])/(l[1]-l[0]), 1, (l[3]-value)/(l[3]-l[2])))
        print("tempp:",res)
        return res
