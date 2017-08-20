'''
Created on May 18, 2017

@author: Nicu
'''


class Humidity:
    def __init__(self, value):
        self.dry = 0
        self.normal = 0
        self.wet = 0
        self.fuzzy(value)

    def triangleFuzzy(self, value, l):
        res = max(0, min((value - l[0])/(l[1] - l[0]), 1, (l[2] - value)/(l[2] - l[1])))
        print("humidity:", res)
        return res

    def fuzzy(self, value):
        self.dry = self.triangleFuzzy(value, [-0.1, 0, 50])
        self.normal = self.triangleFuzzy(value, [-0.1, 50, 100.1])
        self.wet = self.triangleFuzzy(value, [50, 100, 100.1])
