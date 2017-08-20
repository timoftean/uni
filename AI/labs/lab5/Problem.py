from Temperature import Temperature
from Humidity import Humidity
from Time import Time
'''
Created on May 18, 2017

@author: Nicu
'''


class Problem:
    def __init__(self):
        self.temperatureList = [-11, 22, 18, -10, -6, 25]
        self.humidityList = [88, 55, 100, 0, 74, 22, 99]
        self.l = []
        self.computeTime()

    def computeTime(self):
        for i in range(0, len(self.temperatureList)):
            temperature = Temperature(self.temperatureList[i])
            humidity = Humidity(self.humidityList[i])
            time = Time(temperature, humidity)
            self.l.append(int(time.defuzzy()))

    def getTime(self):
        return self.l
