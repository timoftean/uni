'''
Created on May 18, 2017

@author: Nicu
'''


class Time:
    def __init__(self, temperature, humidity):
        self.short = max(min(humidity.wet, 1-temperature.hot), min(humidity.normal, temperature.veryCold))
        self.long = max(min(humidity.dry, 1-temperature.veryCold), min(humidity.normal, temperature.hot))
        self.medium = min(self.short, self.long)
    #     self.write()
    #
    # def write(self):
    #     f = open("description.txt", "a+")
    #     f.write("TIME ")
    #     f.write("short = " + str(self.short) + " = ")
    #     f.write("long = " + str(self.long) + " = ")
    #     f.write("medium = " + str(self.short) + " = ")


    def defuzzy(self):
        res = 50 * (1 - self.short) + 50 * self.long
        f = open("description.txt", "a+")
        f.write(" \n defuzzyficate = " + str(res) + " = " + str( "50 * (1 - " + str(self.short) + ") + (50 * "+ str(self.long)+")") )
        print("time:", self.short, self.long, res)
        return res
